package gb.ru.example_hw5.repository;

import gb.ru.example_hw5.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
// класс Репозиторий
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}
