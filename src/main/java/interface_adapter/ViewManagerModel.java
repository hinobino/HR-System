package interface_adapter;

/**
 * Model to manage the currently displayed view.
 * The state of the model is type String and is the name of the view currently displayed.
 */

public class ViewManagerModel extends ViewModel<String> {

    public ViewManagerModel() {
        super("view manager");
        this.setState("");
    }
}
