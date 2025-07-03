package mediinfo.java.tt.froad.controller;


import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import mediinfo.java.tt.froad.service.ActivityService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "ActivityController", description = "活动")
@RequiredArgsConstructor
@RequestMapping({"api/v1/activity"})
public class ActivityController {

    private final ActivityService activityService;
}
