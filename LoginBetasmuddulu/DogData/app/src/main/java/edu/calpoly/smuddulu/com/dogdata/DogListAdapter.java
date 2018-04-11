package edu.calpoly.smuddulu.com.dogdata;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * This class binds the visual JokeViews and the data behind them (Jokes).
 */
public class DogListAdapter extends BaseAdapter{

	/** The application Context in which this JokeListAdapter is being used. */
	private Context m_context;

	/** The data set to which this JokeListAdapter is bound. */
	private List<Dog> m_doglist;

	/**
	 * Parameterized constructor that takes in the application Context in which
	 * it is being used and the Collection of Joke objects to which it is bound.
	 * m_nSelectedPosition will be initialized to Adapter.NO_SELECTION.
	 * 
	 * @param context
	 *            The application Context in which this JokeListAdapter is being
	 *            used.
	 * 
	 * @param dogList
	 *            The Collection of Joke objects to which this JokeListAdapter
	 *            is bound.
	 */
	public DogListAdapter(Context context, List<Dog> dogList) {

		//TODO
		this.m_context=context;
		this.m_doglist=dogList;
	}

	@Override
	public int getCount() {
		return m_doglist.size();
	}

	@Override
	public Object getItem(int position) {
		return m_doglist.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		DogView dogView=(DogView)convertView;
		if(dogView == null) {
			dogView=new DogView(m_context,m_doglist.get(position));
		}
		else {
		dogView.setDog(m_doglist.get(position));
		}
		return dogView;
	}
}
