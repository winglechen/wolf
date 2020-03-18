package study.daydayup.wolf.business.union.admin.controller.task;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import study.daydayup.wolf.business.account.auth.agent.Session;
import study.daydayup.wolf.business.org.api.task.domain.enums.TaskTypeEnum;
import study.daydayup.wolf.business.org.api.task.dto.TaskId;
import study.daydayup.wolf.business.org.api.task.dto.TaskOption;
import study.daydayup.wolf.business.org.api.task.dto.request.task.ProjectRequest;
import study.daydayup.wolf.business.org.api.task.dto.request.task.StaffRequest;
import study.daydayup.wolf.business.org.api.task.dto.request.task.TaskTypeRequest;
import study.daydayup.wolf.business.org.api.task.service.TaskService;
import study.daydayup.wolf.business.org.api.task.service.task.CollectionTaskService;
import study.daydayup.wolf.business.union.admin.dto.TaskAssignRequest;
import study.daydayup.wolf.framework.layer.web.Controller;
import study.daydayup.wolf.framework.rpc.Result;
import study.daydayup.wolf.business.org.api.task.domain.entity.Task;
import study.daydayup.wolf.framework.rpc.page.Page;
import study.daydayup.wolf.framework.rpc.page.PageRequest;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * study.daydayup.wolf.business.union.admin.controller.task
 *
 * @author Wingle
 * @since 2020/3/17 5:07 下午
 **/
@RestController
public class TaskController implements Controller {
    @Reference
    private TaskService taskService;
    @Reference
    private CollectionTaskService collectionService;
    @Resource
    private Session session;

    @GetMapping("/task/task/{taskId}")
    public Result<Task> find(@PathVariable("taskId") Long taskId) {
        Long orgId = session.get("orgId", Long.class);
        TaskOption option = TaskOption.builder()
                .withDetail(true)
                .withContact(true)
                .withTrade(true)
                .build();

        return taskService.find(taskId, orgId, option);
    }

    @GetMapping("/task/contact/{taskId}")
    public Result<Task> findContact(@PathVariable("taskId") Long taskId) {
        Long orgId = session.get("orgId", Long.class);
        TaskOption option = TaskOption.builder()
                .withDetail(true)
                .withContact(true)
                .build();

        return taskService.find(taskId, orgId, option);
    }

    @GetMapping("/task/collection/{taskId}")
    public Result<Task> findCollection(@PathVariable("taskId") Long taskId) {
        Long orgId = session.get("orgId", Long.class);
        TaskOption option = TaskOption.builder()
                .withDetail(true)
                .withTrade(true)
                .build();

        return taskService.find(taskId, orgId, option);
    }

    @PostMapping("/task")
    public Result<Integer> add(@Validated @RequestBody Task task) {
        Long orgId = session.get("orgId", Long.class);
        task.setOrgId(orgId);
        task.setId(null);

        return taskService.add(task);
    }

    @PostMapping("/task/collection")
    public Result<Integer> addCollection(@Validated @RequestBody Task task) {
        Long orgId = session.get("orgId", Long.class);
        task.setOrgId(orgId);
        task.setId(null);
        task.setTaskType(TaskTypeEnum.COLLECTION.getCode());

        return taskService.add(task);
    }

    @PostMapping("/task/contact")
    public Result<Integer> addContact(@Validated @RequestBody Task task) {
        Long orgId = session.get("orgId", Long.class);
        task.setOrgId(orgId);
        task.setId(null);
        task.setTaskType(TaskTypeEnum.CUSTOMER_CONTACT.getCode());

        return taskService.add(task);
    }

    @PutMapping("/task/assign")
    public Result<Integer> assign(@Validated @RequestBody TaskAssignRequest request) {
        request.valid();

        Long orgId = session.get("orgId", Long.class);
        if (null != request.getTaskId()) {
            return taskService.assign(request.getTaskId(), orgId, request.getStaffId());
        }

        return taskService.assign(request.getTaskIds(), orgId, request.getStaffId());
    }

    @PutMapping("/task/collection/partlyPay/{taskId}/{amount}")
    public Result<Integer> partlyPay(@PathVariable("taskId") Long taskId, @PathVariable("amount") BigDecimal amount ) {
        Long orgId = session.get("orgId", Long.class);
        return collectionService.partlyPay(taskId, orgId, amount);
    }

    @PutMapping("/task/collection/confirm/partlyPay/{taskId}/{amount}")
    public Result<Integer> confirmPartlyPay(@PathVariable("taskId") Long taskId, @PathVariable("amount") BigDecimal amount) {
        Long orgId = session.get("orgId", Long.class);
        return collectionService.confirmPartlyPay(taskId, orgId, amount);
    }

    @PutMapping("/task/collection/pay/{taskId}")
    public Result<Integer> pay(@PathVariable("taskId") Long taskId) {
        Long orgId = session.get("orgId", Long.class);
        return collectionService.pay(taskId, orgId);
    }

    @PutMapping("/task/collection/confirm/pay/{taskId}")
    public Result<Integer> confirmPay(@PathVariable("taskId") Long taskId) {
        Long orgId = session.get("orgId", Long.class);
        return collectionService.confirmPay(taskId, orgId);
    }

    @PutMapping("/task/collection/fail/{taskId}")
    public Result<Integer> fail(@PathVariable("taskId") Long taskId) {
        Long orgId = session.get("orgId", Long.class);
        return collectionService.fail(taskId, orgId);
    }

    @PutMapping("/task/collection/confirm/fail/{taskId}")
    public Result<Integer> confirmFail(@PathVariable("taskId") Long taskId) {
        Long orgId = session.get("orgId", Long.class);
        return collectionService.confirmFail(taskId, orgId);
    }

    @GetMapping("/task/all")
    public Result<Page<Task>> findAll(@RequestParam(value = "pageNum", required = false) Integer pageNum) {
        Long orgId = session.get("orgId", Long.class);
        PageRequest pageRequest = PageRequest.builder()
                .pageNum(null == pageNum ? 1 : pageNum)
                .pageSize(10)
                .build();

        return taskService.findByOrg(orgId, pageRequest);
    }

    @GetMapping("/task/subTasks/{taskId}")
    public Result<Page<Task>> findSubTasks(@PathVariable("taskId") Long id, @RequestParam(value = "pageNum", required = false) Integer pageNum) {
        Long orgId = session.get("orgId", Long.class);
        PageRequest pageRequest = PageRequest.builder()
                .pageNum(null == pageNum ? 1 : pageNum)
                .pageSize(10)
                .build();

        TaskOption option = TaskOption.builder()
                .withContact(true)
                .build();

        TaskId taskId = TaskId.builder()
                .orgId(orgId)
                .taskId(id)
                .option(option)
                .build();

        return taskService.findSubTasks(taskId, pageRequest);
    }

    @GetMapping("/task/staff/{staffId}")
    public Result<Page<Task>> findByStaff(@PathVariable("staffId") Long staffId, @RequestParam(value = "pageNum", required = false) Integer pageNum) {
        Long orgId = session.get("orgId", Long.class);
        PageRequest pageRequest = PageRequest.builder()
                .pageNum(null == pageNum ? 1 : pageNum)
                .pageSize(10)
                .build();

        TaskOption option = TaskOption.builder()
                .withTrade(true)
                .build();

        StaffRequest request = StaffRequest.builder()
                .orgId(orgId)
                .staffId(staffId)
                .option(option)
                .build();

        return taskService.findByStaff(request, pageRequest);
    }

    @GetMapping("/task/project/{projectId}")
    public Result<Page<Task>> findByProject(@PathVariable("projectId") Long projectId, @RequestParam(value = "pageNum", required = false) Integer pageNum) {
        Long orgId = session.get("orgId", Long.class);
        PageRequest pageRequest = PageRequest.builder()
                .pageNum(null == pageNum ? 1 : pageNum)
                .pageSize(10)
                .build();

        ProjectRequest request = ProjectRequest.builder()
                .orgId(orgId)
                .projectId(projectId)
                .build();

        return taskService.findByProject(request, pageRequest);
    }

    @GetMapping("/task/collection")
    public Result<Page<Task>> findCollections(@RequestParam(value = "pageNum", required = false) Integer pageNum) {
        Long orgId = session.get("orgId", Long.class);
        PageRequest pageRequest = PageRequest.builder()
                .pageNum(null == pageNum ? 1 : pageNum)
                .pageSize(10)
                .build();

        TaskOption option = TaskOption.builder()
                .withTrade(true)
                .build();

        TaskTypeRequest request = TaskTypeRequest.builder()
                .orgId(orgId)
                .taskType(TaskTypeEnum.COLLECTION.getCode())
                .option(option)
                .build();

        return taskService.findByTaskType(request, pageRequest);
    }

    @GetMapping("/task/collection/staff/{staffId}")
    public Result<Page<Task>> findStaffCollections(@PathVariable("staffId") Long staffId, @RequestParam(value = "pageNum", required = false) Integer pageNum) {
        Long orgId = session.get("orgId", Long.class);
        PageRequest pageRequest = PageRequest.builder()
                .pageNum(null == pageNum ? 1 : pageNum)
                .pageSize(10)
                .build();

        TaskOption option = TaskOption.builder()
                .withTrade(true)
                .build();

        TaskTypeRequest request = TaskTypeRequest.builder()
                .orgId(orgId)
                .staffId(staffId)
                .taskType(TaskTypeEnum.COLLECTION.getCode())
                .option(option)
                .build();

        return taskService.findByTaskType(request, pageRequest);
    }

    @GetMapping("/task/contact")
    public Result<Page<Task>> findContacts(@RequestParam(value = "pageNum", required = false) Integer pageNum) {
        Long orgId = session.get("orgId", Long.class);
        PageRequest pageRequest = PageRequest.builder()
                .pageNum(null == pageNum ? 1 : pageNum)
                .pageSize(10)
                .build();

        TaskOption option = TaskOption.builder()
                .withContact(true)
                .build();

        TaskTypeRequest request = TaskTypeRequest.builder()
                .orgId(orgId)
                .taskType(TaskTypeEnum.CUSTOMER_CONTACT.getCode())
                .option(option)
                .build();

        return taskService.findByTaskType(request, pageRequest);
    }

    @GetMapping("/task/contact/staff/{staffId}")
    public Result<Page<Task>> findStaffContacts(@PathVariable("staffId") Long staffId, @RequestParam(value = "pageNum", required = false) Integer pageNum) {
        Long orgId = session.get("orgId", Long.class);
        PageRequest pageRequest = PageRequest.builder()
                .pageNum(null == pageNum ? 1 : pageNum)
                .pageSize(10)
                .build();

        TaskOption option = TaskOption.builder()
                .withContact(true)
                .build();

        TaskTypeRequest request = TaskTypeRequest.builder()
                .orgId(orgId)
                .staffId(staffId)
                .taskType(TaskTypeEnum.CUSTOMER_CONTACT.getCode())
                .option(option)
                .build();

        return taskService.findByTaskType(request, pageRequest);
    }


}
