package com.nt.ifeign;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("IPL_PLAYER")
public interface FeingClientComm {

}
