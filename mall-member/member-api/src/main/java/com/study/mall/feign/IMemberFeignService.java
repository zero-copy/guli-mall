package com.study.mall.feign;

import com.study.mall.common.lang.R;
import com.study.mall.dto.MemberLoginDto;
import com.study.mall.dto.MemberRegisterDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

/**
 * @author Harlan
 * @date 2022 06 10 下午 05:30
 */
@FeignClient(contextId = "member", value = "mall-member", path = "/member/member")
public interface IMemberFeignService {

    @PostMapping("/register")
    R register(@Valid @RequestBody MemberRegisterDto registerVo);

    @PostMapping("/login")
    public R login(@RequestBody MemberLoginDto loginDto);
}
