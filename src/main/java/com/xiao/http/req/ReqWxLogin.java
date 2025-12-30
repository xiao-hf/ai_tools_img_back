package com.xiao.http.req;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ReqWxLogin {

    @Schema(description = "微信小程序 openId")
    @JsonProperty("openId")
    @JsonAlias({"openid", "openID", "open_id", "oipenId"})
    @NotBlank(message = "openId 不能为空")
    private String openId;

    @Schema(description = "小程序 appId，可选")
    @Size(max = 255, message = "appId 过长")
    private String appId;

    @Schema(description = "密钥，appId 为空时用于校验凭证")
    @Size(max = 255, message = "secretKey 过长")
    private String secretKey;
}
