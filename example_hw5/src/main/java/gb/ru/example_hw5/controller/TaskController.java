package gb.ru.example_hw5.controller;

import gb.ru.example_hw5.model.Task;
import gb.ru.example_hw5.model.TaskStatus;
import gb.ru.example_hw5.repository.TaskRepository;
import gb.ru.example_hw5.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
// Класс контроллер
@RestController
@RequestMapping("/api")
@AllArgsConstructor // конструктор со всеми полями класса
public class TaskController {
    @Autowired // автоматическое включение конструктора
    TaskService taskService;

    // метод - показать все Task
    @GetMapping("/tasks") // путь http://localhost:8080/tasks
    public List<Task> getAllTasks(){
        return taskService.getTasks();
    }


    //  метод сздания Task
    @PostMapping("/creat-task") // путь http://localhost:8080/creat-task
    public void creatTask(@RequestBody Task task){
        taskService.creatTask(task);
    }

    //  метод изменения статуса на IN_PROGRESS
    @PutMapping("task/status-progress/{id}") // путь http://localhost:8080/task/status-progress/{id}
    public Task updateTaskStatusIN_PROGRESS(@PathVariable Long id, @RequestBody Task task){
        return taskService.updateTaskStatusIN_PROGRESS(id, task);
    }
    // метод изменения статуса на COMPLETED
    @PutMapping("task/status-complete/{id}")// путь http://localhost:8080/task/status-complete/{id}
    public Task updateTaskStatusCOMPLETED(@PathVariable Long id, @RequestBody Task task){
        return taskService.updateTaskStatusCOMPLETED(id, task);
    }

    // метод удаления по id
    @DeleteMapping("/task/{id}") // путь  http://localhost:8080/task/{id}
    public void deleteTask(@PathVariable Long id){
        taskService.delTask(id);
    }

    // метод - список сортировки статуса
    @GetMapping("/status/{status}")// путь http://localhost:8080/status/IN_PROGRESS
    public List<Task> getTasksStatus(@PathVariable TaskStatus status){
        return taskService.getTasksByStatus(status);
    }
}
