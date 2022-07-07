package com.pick.dto.response;

import com.pick.dto.base.ResponseData;
import com.pick.model.TalkContentDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class TalkContentsResDto extends ResponseData {
    private List<TalkContentDto> talkContents;
    private boolean hasNext;

    public TalkContentsResDto(List<TalkContentDto> talkContents) {
        if (talkContents.size() > 50) {
            this.hasNext = true;
            talkContents.remove(50);
        } else {
            this.hasNext = false;
        }
        this.talkContents = talkContents;
    }
}
