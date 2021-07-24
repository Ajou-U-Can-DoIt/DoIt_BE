package Ajou.ucandoit.repository;

import Ajou.ucandoit.domain.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalendarRepository extends JpaRepository<Calendar, Long> {

}
