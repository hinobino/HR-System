package interface_adapter;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * The ViewModel for our CA implementation.
 * This class delegates work to a PropertyChangeSupport object for
 * managing the property change events.
 *
 * @param <T> The type of state object contained in the model.
 */
public class ViewModel<T> {

    private final String viewName;

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    private T state;

    /**
     * A ViewModel Constructor.
     * @param viewName the name of the current view.
     */
    public ViewModel(String viewName) {
        this.viewName = viewName;
    }

    /**
     * Return the name of the current view.
     * @return the name of the current view.
     */
    public String getViewName() {
        return this.viewName;
    }

    /**
     * Return the current state.
     * @return the current state.
     */
    public T getState() {
        return this.state;
    }

    /**
     * Update the state.
     * @param state the new state of the model of type T.
     */
    public void setState(T state) {
        this.state = state;
    }

    /**
     * Fires an event when the current state of the view model is updated.
     */
    public void firePropertyChanged() {
        this.support.firePropertyChange("state", null, this.state);
    }

    /**
     * Fires a property changed event for the state of this ViewModel, which
     * allows the user to specify a different propertyName. This can be useful
     * when a class is listening for multiple kinds of property changes.
     * For example, the ManagerView listens for two kinds of property changes;
     * it can use the property name to distinguish which property has changed.
     * @param propertyName the label for the property that was changed
     */
    public void firePropertyChanged(String propertyName) {
        this.support.firePropertyChange(propertyName, null, this.state);
    }
    /**
     * Fires a property changed event with old and new values specified.
     *
     * @param propertyName the name of the property that has changed
     * @param oldValue the old value of the property
     * @param newValue the new value of the property
     */
    public void firePropertyChanged(String propertyName, Object oldValue, Object newValue) {
        this.support.firePropertyChange(propertyName, oldValue, newValue);
    }


    /**
     * Adds a PropertyChangeListener to this ViewModel.
     * @param listener The PropertyChangeListener to be added
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.support.addPropertyChangeListener(listener);
    }
}
