package spring.practice.project.domain.category;

public class Category {

    private Long id;
    private String title;
    private String info;

    public Category(final String title, final String info) {
        this.title = title;
        this.info = info;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

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
