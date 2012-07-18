package de.oose.taskboard.client.util;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

/** A small utility class which displays the exceptions message on failure to a Window.
 * 
 * @author markusklink
 *
 * @param <T> the result type of the asynchronous callback
 */
public abstract class DefaultAsyncCallback<T> implements AsyncCallback<T>{

	@Override
	public void onFailure(Throwable caught) {
		Window.alert(caught.getMessage());
	}

	@Override
	public abstract void onSuccess(T result) ;

}
