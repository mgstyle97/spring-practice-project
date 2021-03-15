package spring.practice.project.domain.category.commond;

import javax.validation.constraints.NotBlank;

public class CategoryRegisterCommand {

    @NotBlank
    private String title;
    @NotBlank
    private String info;

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }

    public void setInfo(final String info) {
        this.info = info;
    }

    public String getInfo() {
        return this.info;
    }

}
