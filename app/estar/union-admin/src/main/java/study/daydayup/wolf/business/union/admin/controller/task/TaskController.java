package study.daydayup.wolf.business.union.admin.controller.task;

import org.springframework.web.bind.annotation.*;
import study.daydayup.wolf.framework.layer.web.Controller;
import study.daydayup.wolf.framework.rpc.Result;
import study.daydayup.wolf.business.org.api.task.domain.entity.Task;
import study.daydayup.wolf.framework.rpc.page.Page;

/**
 * study.daydayup.wolf.business.union.admin.controller.task
 *
 * @author Wingle
 * @since 2020/3/17 5:07 下午
 **/
@RestController
public class TaskController implements Controller {

    @GetMapping("/task/{taskId}")
    public Result<Task> find(@PathVariable("taskId") Long taskId) {
        return null;
    }

    @PostMapping("/task")
    public Result<Integer> add() {
        return null;
    }

    @PutMapping("/task/assign")
    public Result<Integer> assign() {
        return null;
    }

    @PutMapping("/task/collection/partlyPay")
    public Result<Integer> partlyPay() {
        return null;
    }

    @PutMapping("/task/collection/confirm/partlyPay")
    public Result<Integer> confirmPartlyPay() {
        return null;
    }

    @PutMapping("/task/collection/pay")
    public Result<Integer> pay() {
        return null;
    }

    @PutMapping("/task/collection/confirm/pay")
    public Result<Integer> confirmPay() {
        return null;
    }

    @PutMapping("/task/collection/fail")
    public Result<Integer> fail() {
        return null;
    }

    @PutMapping("/task/collection/confirm/asLoss")
    public Result<Integer> confirmAsLoss() {
        return null;
    }


    public Result<Page<Task>> findAll() {
        return null;
    }

    public Result<Page<Task>> findSubTasks() {
        return null;
    }

    public Result<Page<Task>> findByStaff() {
        return null;
    }

    public Result<Page<Task>> findByProject() {
        return null;
    }

    public Result<Page<Task>> findCollection() {
        return null;
    }

    public Result<Page<Task>> findContact() {
        return null;
    }





}
