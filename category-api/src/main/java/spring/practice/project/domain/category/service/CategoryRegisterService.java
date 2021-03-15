package spring.practice.project.domain.category.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import spring.practice.project.domain.category.Category;
import spring.practice.project.domain.category.CategoryDao;
import spring.practice.project.domain.category.commond.CategoryRegisterCommand;

@Component
public class CategoryRegisterService {

    private CategoryDao dao;

    @Autowired
    public void setDao(final CategoryDao dao) {
        this.dao = dao;
    }

    public CategoryDao getDao() {
        return this.dao;
    }

    @Transactional
    public void register(final CategoryRegisterCommand command) {
        Category category = new Category(command.getTitle(), command.getInfo());

        dao.insert(category);
    }

}
