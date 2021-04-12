package spring.practice.project.domain.category.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import spring.practice.project.domain.category.Category;
import spring.practice.project.domain.category.CategoryDao;
import spring.practice.project.domain.category.commond.CategoryModifyCommand;
import spring.practice.project.domain.global.exception.InvalidApproachException;
import spring.practice.project.domain.user.User;

@Component
public class CategoryModifyService {

    private CategoryDao dao;

    @Autowired
    public void setDao(final CategoryDao dao) {
        this.dao = dao;
    }

    @Transactional
    public void modify(final CategoryModifyCommand command) {
        Category category = this.dao.selectById(command.getId());
        if(category == null) {
            throw new InvalidApproachException();
        }

        Category updateCategory = updateCategoryToCommand(category, command);
        this.dao.update(updateCategory);
    }

    private Category updateCategoryToCommand(final Category category, final CategoryModifyCommand command) {
        String updateTitle = command.getTitle();
        String updateInfo = command.getInfo();

        if (updateTitle != null && !category.getTitle().equals(updateTitle)) {
            category.setTitle(updateTitle);
        }
        if (updateInfo != null && !category.getInfo().equals(updateInfo)) {
            category.setInfo(updateInfo);
        }

        return category;
    }

}
