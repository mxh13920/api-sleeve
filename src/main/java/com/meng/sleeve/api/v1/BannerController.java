package com.meng.sleeve.api.v1;

import com.meng.sleeve.core.annotations.ScopeLevel;
import com.meng.sleeve.exception.http.NotFoundException;
import com.meng.sleeve.model.Banner;
import com.meng.sleeve.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;

//@Controller
//@ResponseBody
@RestController
@RequestMapping("/banner")
@Validated
public class BannerController {

    @Autowired
    private BannerService bannerService;

    @GetMapping("/name/{name}")
//    @ScopeLevel
    public Banner getByName(@PathVariable @NotBlank String name) {
        Banner banner = bannerService.getByName(name);
        if (banner == null) {
            throw  new NotFoundException(30005);
        }
        return banner;
    }

}
