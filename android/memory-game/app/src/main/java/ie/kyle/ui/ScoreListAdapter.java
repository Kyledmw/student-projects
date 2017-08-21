package ie.kyle.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import ie.kyle.data.IScore;
import ie.kyle.memorygame.R;

/**
 ********************************************************************
 * Adapter for a {@link List<IScore>} for a {@link ListView} component
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public class ScoreListAdapter extends ArrayAdapter<IScore> {

    public ScoreListAdapter(Context context, List<IScore> scores) {
        super(context, 0, scores);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        IScore score = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_score, parent, false);
        }

        TextView scoreName = (TextView) convertView.findViewById(R.id.score_item_name);
        TextView scoreScore = (TextView) convertView.findViewById(R.id.score_item_score);

        scoreName.setText(score.getName());
        scoreScore.setText("" + score.getScore());

        return convertView;
    }
}
