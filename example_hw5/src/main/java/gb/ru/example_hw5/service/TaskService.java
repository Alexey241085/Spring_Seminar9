package gb.ru.example_hw5.service;

import gb.ru.example_hw5.model.Task;
import gb.ru.example_hw5.model.TaskStatus;
import gb.ru.example_hw5.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskService {

   private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    // метод вызова всех Task
    public List<Task> getTasks(){
        return taskRepository.findAll();
    }

    // метод создания Task, дата и время создается автоматически, статус по умолчанию NOT_STARTED
    public void creatTask(Task task){
        task.setStringLocalDateTime(LocalDateTime.now().toString());
        task.setStatus(TaskStatus.NOT_STARTED);
        taskRepository.save(task);
    }

// метод удаления Task по id
    public void delTask(Long id){
        taskRepository.deleteById(id);
    }

// метод изменения статуса по id на IN_PROGRESS
    public Task updateTaskStatusIN_PROGRESS(Long id, Task taskDetails){
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            task.setStatus(TaskStatus.IN_PROGRESS);
            return taskRepository.save(task);
        } else {
            throw new IllegalArgumentException("Task not found with id: " + id);
        }
    }

    // метод изменения статуса по id на COMPLETED
    public Task updateTaskStatusCOMPLETED(Long id, Task taskDetails){
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            task.setStatus(TaskStatus.COMPLETED);
            return taskRepository.save(task);
        } else {
            throw new IllegalArgumentException("Task not found with id: " + id);
        }
    }

    //метод сортировки Task по статуcу
    public List<Task> getTasksByStatus(TaskStatus status){
         List<Task> taskList = taskRepository.findAll();
        return taskList.stream()
                .filter(it -> it.getStatus().name().equalsIgnoreCase(status.name()))
                .collect(Collectors.toList());
    }
}


