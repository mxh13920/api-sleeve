package com.meng.sleeve.api.v1;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import com.meng.sleeve.exception.http.NotFoundException;
import com.meng.sleeve.model.Theme;
import com.meng.sleeve.service.ThemSerive;
import com.meng.sleeve.vo.ThemePureVO;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/theme")
public class ThemeController {

    @Autowired
    private ThemSerive themSerive;

    @GetMapping("/by/names")
    public List<ThemePureVO> getByName(@RequestParam(name = "names") String names) {
        List<String> nameList = Arrays.asList(names.split(","));
        List<Theme> themes = themSerive.getThemByName(nameList);
//     return themes;
        List<ThemePureVO> list = new ArrayList<>();
        themes.forEach(theme -> {
            Mapper mapper = DozerBeanMapperBuilder.buildDefault();
            ThemePureVO vo = mapper.map(theme, ThemePureVO.class);
            list.add(vo);
        });
        return list;
    }

    @GetMapping("/name/{name}/with_spu")
    public Theme getOneThemeSpu(@PathVariable String name) {
        Optional<Theme> oneTheme = themSerive.getOneTheme(name);
        return oneTheme.orElseThrow(() -> new NotFoundException(30003));
    }

    @GetMapping("/all")
    public List<Theme> getAllThemes(){
        return themSerive.getAllTheme();
    }

}
