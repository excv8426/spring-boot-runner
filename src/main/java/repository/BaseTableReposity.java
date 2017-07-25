package repository;

import model.BaseTable;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BaseTableReposity<T extends BaseTable> extends JpaRepository<T, String> {

}
