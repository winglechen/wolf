package study.daydayup.wolf.business.union.admin.controller.task;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import study.daydayup.wolf.business.account.auth.agent.Session;
import study.daydayup.wolf.business.org.api.task.dto.TaskId;
import study.daydayup.wolf.business.org.api.task.dto.TaskOption;
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

        return taskService.find(taskId, orgId);
    }

    @GetMapping("/task/contact/{taskId}")
    public Result<Task> findContact(@PathVariable("taskId") Long taskId) {
        Long orgId = session.get("orgId", Long.class);
        TaskOption option = TaskOption.builder()
                .withContact(true)
                .build();

        return taskService.find(taskId, orgId, option);
    }

    @GetMapping("/task/collection/{taskId}")
    public Result<Task> findCollection(@PathVariable("taskId") Long taskId) {
        Long orgId = session.get("orgId", Long.class);
        TaskOption option = TaskOption.builder()
                .withTrade(true)
                .build();

        return taskService.find(taskId, orgId, option);
    }

    @PostMapping("/task")
    public Result<Integer> add(@Validated @RequestBody Task task) {
        Long orgId = session.get("orgId", Long.class);
        task.setOrgId(orgId);

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

    @PutMapping("/task/collection/confirm/partlyPay/{taskId}")
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

    @PutMapping("/task/collection/confirm/asLoss/{taskId}")
    public Result<Integer> confirmAsLoss(@PathVariable("taskId") Long taskId) {
        Long orgId = session.get("orgId", Long.class);
        return collectionService.confirmAsLoss(taskId, orgId);
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
    public Result<Page<Task>> findByStaff(@RequestParam(value = "pageNum", required = false) Integer pageNum) {
        Long orgId = session.get("orgId", Long.class);
        PageRequest pageRequest = PageRequest.builder()
                .pageNum(null == pageNum ? 1 : pageNum)
                .pageSize(10)
                .build();
        return null;
    }

    @GetMapping("/task/project/{projectId}")
    public Result<Page<Task>> findByProject(@RequestParam(value = "pageNum", required = false) Integer pageNum) {
        Long orgId = session.get("orgId", Long.class);
        PageRequest pageRequest = PageRequest.builder()
                .pageNum(null == pageNum ? 1 : pageNum)
                .pageSize(10)
                .build();

        return null;
    }

    @GetMapping("/task/collection")
    public Result<Page<Task>> findCollections(@RequestParam(value = "pageNum", required = false) Integer pageNum) {
        Long orgId = session.get("orgId", Long.class);
        PageRequest pageRequest = PageRequest.builder()
                .pageNum(null == pageNum ? 1 : pageNum)
                .pageSize(10)
                .build();

        return null;
    }


    @GetMapping("/task/contact")
    public Result<Page<Task>> findContacts(@RequestParam(value = "pageNum", required = false) Integer pageNum) {
        Long orgId = session.get("orgId", Long.class);
        PageRequest pageRequest = PageRequest.builder()
                .pageNum(null == pageNum ? 1 : pageNum)
                .pageSize(10)
                .build();

        return null;
    }






}
