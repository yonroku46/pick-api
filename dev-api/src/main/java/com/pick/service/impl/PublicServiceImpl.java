package com.pick.service.impl;

import com.pick.dto.base.ResponseData;
import com.pick.dto.request.*;
import com.pick.dto.response.BooleanResDto;
import com.pick.dto.response.ImgUploadResDto;
import com.pick.dto.response.LoginResDto;
import com.pick.dto.response.MyFavoritesResDto;
import com.pick.model.EmailTO;
import com.pick.repository.BookingRepository;
import com.pick.repository.UserRepository;
import com.pick.service.EmailService;
import com.pick.service.PublicService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Tuple;
import java.io.File;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
@RestControllerAdvice
@RequiredArgsConstructor
public class PublicServiceImpl implements PublicService {

    private Integer NORMAL_ROLE = 1;

    private final PasswordEncoder passwordEncoder;

    private final EmailService emailService;
    private final UserRepository userRepository;
    private final BookingRepository bookingRepository;

    @Value("${property.public.path}")
    private String publicPath;

//    private final String publicPath = "C:/git/pick-viewer/dev-viewer/public/";

    @Override
    public ResponseData login(LoginReqDto req) {
        String userEmail = req.getUserEmail();
        String userPw = req.getUserPw();
        LoginResDto response = new LoginResDto(userRepository.login(userEmail, userPw));
        return response;
    }

    @Override
    public List<ResponseData> myFavorites(MyFavoritesReqDto req) {
        Integer userCd = req.getUserCd();
        List<Tuple> tuples = userRepository.myFavorites(userCd);
        List<ResponseData> response = new ArrayList<>();
        for(Tuple tuple : tuples) {
            response.add(new MyFavoritesResDto(tuple));
        }
        return response;
    }

    @Override
    public ResponseData bookingCheck(BookingCheckReqDto req) {
        Integer userCd = req.getUserCd();
        Timestamp bookingTime = req.getBookingTime();
        BooleanResDto response = new BooleanResDto();
        int count = bookingRepository.bookingCheck(userCd, bookingTime);
        if (count == 0) {
            response.setResult(true);
        } else {
            response.setResult(false);
        }
        return response;
    }

    @Override
    public ResponseData mailCheck(MailCheckReqDto req) {
        String userEmail = req.getUserEmail();
        BooleanResDto response = new BooleanResDto();
        int cnt = userRepository.emailCount(userEmail);
        if (cnt == 1) {
            try {
                Random random = new Random();
                EmailTO email = new EmailTO();

                String pin = "";
                for (int i = 0; i < 6; i++) {
                    pin += (random.nextInt(9) + 1);
                }

                email.setReceiveMail(userEmail);
                email.setSubject("[Pick] 본인확인용 인증 안내");
                email.setMessage("본인확인용 인증 메일입니다.\n본인이 아닐 시 즉시 문의 바랍니다.\n\n인증번호 : " + pin + "\n\n(본 이메일은 발신전용이며 회신이 되지 않습니다.)");

                if (emailService.sendMail(email)) {
                    userRepository.updatePin(pin, userEmail);
                    response.setResult(true);
                } else {
                    response.setResult(false);
                }
            } catch (Exception e) {
                response.setResult(false);
            }
        } else {
            response.setResult(false);
        }
        return response;
    }

    @Override
    public ResponseData certification(CertificationReqDto req) {
        String userEmail = req.getUserEmail();
        String pin = req.getPin();
        BooleanResDto response = new BooleanResDto();
        int cnt = userRepository.pinCount(pin, userEmail);
        if (cnt == 1) {
            userRepository.pinReset(userEmail);
            response.setResult(true);
        } else {
            response.setResult(false);
        }
        return response;
    }

    @Override
    public ResponseData resetPwd(ResetPwdReqDto req) {
        String userEmail = req.getUserEmail();
        String userPw = req.getUserPw();
        BooleanResDto response = new BooleanResDto();
        userRepository.resetPwd(userEmail, passwordEncoder.encode(userPw));
        response.setResult(true);
        return response;
    }

    @Override
    public ResponseData mailService(MailServiceReqDto req) {
        String userEmail = req.getUserEmail();
        Integer role = req.getRole();
        BooleanResDto response = new BooleanResDto();
        int cnt = userRepository.emailCount(userEmail);
        if (cnt == 1) {
            response.setResult(false);
        } else {
            try {
                Random random = new Random();
                EmailTO email = new EmailTO();

                String pin = "";
                for (int i = 0; i < 6; i++) {
                    pin += (random.nextInt(9) + 1);
                }

                email.setReceiveMail(userEmail);
                email.setSubject("[Pick] 회원가입 인증 안내");
                email.setMessage("회원가입용 인증 메일입니다.\n본인이 아닐 시 즉시 문의 바랍니다.\n\n인증번호 : " + pin + "\n\n직원/사업자 회원은 영업일기준 1일이내, 본 메일로 상세 등록절차를 안내해 드립니다.\n\n(본 이메일은 발신전용이며 회신이 되지 않습니다.)");

                emailService.sendMail(email);
                if (role == NORMAL_ROLE) {
                    userRepository.signupNormal(userEmail, role, pin);
                } else {
                    userRepository.signupStaff(userEmail, role, pin);
                }
                response.setResult(true);
            } catch (Exception e) {
                response.setResult(false);
            }
        }
        return response;
    }

    @Override
    public ResponseData signup(SignupReqDto req) {
        String userEmail = req.getUserEmail();
        String userName = req.getUserName();
        String userPw = req.getUserPw();
        BooleanResDto response = new BooleanResDto();
        userRepository.signup(userEmail, userName, passwordEncoder.encode(userPw));
        response.setResult(true);
        return response;
    }

    @Override
    public ResponseData imgUpload(ImgUploadReqDto req) {
        MultipartFile file = req.getFile();
        Integer userCd = req.getUserCd();
        Integer shopCd = req.getShopCd();
        Integer menuCd = req.getMenuCd();
        String call = req.getCall();
        ImgUploadResDto response = new ImgUploadResDto();

        try {
            String imgPath = "";

            Date now = new Date(System.currentTimeMillis());
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
            String datetime = dateFormat.format(now);

            // 파일명 생성
            if (call.equals("user")) {
                imgPath = "images/" + call + "/" + userCd + ".png";
            } else if (call.equals("shop")) {
                String imgIndex = "0";
                String fileName = imgIndex + "_" + datetime + ".png";
                imgPath = "images/" + call + "/" + shopCd + "/tmp/" + fileName;
            } else if (call.equals("menu")) {
                String fileName = menuCd + "_" +datetime + ".png";
                imgPath = "images/" + call + "/" + shopCd + "/tmp/" + fileName;
            }

            if (!file.isEmpty()) {
                // jpeg, png, gif 파일인지 체크
                String contentType = file.getContentType();
                if (!(contentType.contains("image/jpeg") || contentType.contains("image/jpg") || contentType.contains("image/png"))) {
                    response.setResult(false);
                }
                // 파일 작성
                File folder = new File(publicPath);
                if (!folder.exists()) {
                    folder.mkdirs();
                }
                File destination = new File(publicPath + imgPath);
                file.transferTo(destination);

                userRepository.userImgUpdate(imgPath, userCd);
                response.setImgPath(imgPath);
                response.setResult(true);
            }
        } catch (Exception e){
            response.setResult(false);
        }
        return response;
    }

}
