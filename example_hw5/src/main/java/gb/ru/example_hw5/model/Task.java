package gb.ru.example_hw5.model;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
//@Data // создание геттеров и сеттеров, создание конструктора и to.String
@Entity // сущность
@Table(name = "task") // название таблицы
@NoArgsConstructor // конструктор без аргументов
public class Task implements Serializable {
//    @Transient
//    private transient TaskStatus taskStatus;
//    @Transient
//   private transient LocalDateTime  localDateTime = LocalDateTime.now();



    @Id // показываем что данное поле id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // автогенерация id
    private Long id;
    @Column( name ="description", nullable = false) // назван ие колонки в таблице, поле не должно быть пустым (not null)
    private String description;
    @Column( name ="status") // назван ие колонки в таблице
//    private String stringTaskStatus ;
    @Enumerated(EnumType.STRING) // enum в таблице отражается строкой
    private TaskStatus status;
    @Column(name = "date_time") // назван ие колонки в таблице
    private String stringLocalDateTime ;
}
