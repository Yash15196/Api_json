package location.com.api_json;

public class model {
    String author,title,description;

    public model(String author,String title,String description){
       this.setAuthor(author);
       this.setTitle(title);
       this.setDescription(description);
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {

        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {

        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
