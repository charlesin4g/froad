package mediinfo.java.tt.froad.controller;


import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import mediinfo.java.tt.froad.service.PlanService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "PlanController", description = "计划")
@RequiredArgsConstructor
@RequestMapping({"api/v1/plan"})
public class PlanController {

    private final PlanService planService;
}
