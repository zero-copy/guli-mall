package com.study.mall.feign;

import com.study.mall.common.lang.R;
import com.study.mall.dto.MemberAddressDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author Harlan
 * @date 2022 06 21 下午 01:37
 */
@FeignClient(contextId = "member-address", value = "mall-member", path = "/member/memberreceiveaddress")
public interface IMemberAddressFeignService {

    @GetMapping("/{memberId}/address")
    R<List<MemberAddressDto>> getAddress(@PathVariable("memberId") Long memberId);
}
