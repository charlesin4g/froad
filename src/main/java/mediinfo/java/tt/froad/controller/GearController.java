package mediinfo.java.tt.froad.controller;


import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import mediinfo.java.tt.froad.service.GearService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "GearController", description = "装备")
@RequiredArgsConstructor
@RequestMapping({"api/v1/gear"})
public class GearController {

    private final GearService gearService;
}
