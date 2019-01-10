        package phonebrandsystem.sxd.hsh.phonebrandsystem;

        import android.annotation.SuppressLint;
        import android.app.Activity;
        import android.app.AlertDialog;
        import android.content.Intent;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.BaseAdapter;
        import android.widget.Button;
        import android.widget.TextView;
        import android.widget.Toast;

        import java.util.List;

        public class IBaseAdapter extends BaseAdapter {
            private List<Phone> list;
            private Activity activity;
            private AlertDialog dialog ;

            public IBaseAdapter(Activity activity,List<Phone> list) {
                this.list = list;
                this.activity = activity;
            }

            @Override
            public int getCount() {
                return list.size();
            }

            @Override
            public Object getItem(int position) {
                return list.get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @SuppressLint("ViewHolder")
            @Override
            public View getView(final int position, View convertView, ViewGroup parent) {
                ViewHolder viewHolder = null;
                if (viewHolder==null){
                    convertView = LayoutInflater.from(activity).inflate(R.layout.item_main, parent, false);
                    viewHolder = new ViewHolder(convertView);
                    convertView.setTag(viewHolder);
                }else {
                        viewHolder = (ViewHolder) convertView.getTag();
                }

                viewHolder.tvName.setText(list.get(position).getName());
                viewHolder.tvCpu.setText(list.get(position).getCpu());
                viewHolder.tvDc.setText(list.get(position).getBattery());

                viewHolder.rootView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        View view = LayoutInflater.from(activity).inflate(R.layout.dialog,null,false);
                        Button del = view.findViewById(R.id.del);
                        Button update = view.findViewById(R.id.update);
                        dialog = new AlertDialog.Builder(activity)
                                .setView(view)
                                .show();
                        del.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                DBAdapter dbAdapter = new DBAdapter(activity);
                                boolean delete = dbAdapter.delete(list.get(position));
                                if (delete){
                                    Toast.makeText(activity, "删除成功!", Toast.LENGTH_SHORT).show();
                                    list.remove(position);
                                    notifyDataSetChanged();
                                   if (dialog!=null){
                                       dialog.dismiss();
                                   }
                                }else {
                                     Toast.makeText(activity, "删除失败!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                        update.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(activity, UpdateActivity.class);
                                intent.putExtra("message",list.get(position));
                                activity.startActivityForResult(intent,1);
                                dialog.dismiss();
                            }
                        });

                    }
                });
                return convertView;
            }

            public void refresh() {
                DBAdapter dbAdapter = new DBAdapter(activity);
                List<Phone> list = dbAdapter.query();
                this.list = list;
                notifyDataSetChanged();
            }

            public static class ViewHolder {
                public View rootView;
                public TextView tvName;
                public TextView tvCpu;

                public TextView tvDc;
                public ViewHolder(View rootView) {
                    this.rootView = rootView;

                    this.tvName = (TextView) rootView.findViewById(R.id.tvName);
                    this.tvCpu = (TextView) rootView.findViewById(R.id.tvCpu);
                    this.tvDc = (TextView) rootView.findViewById(R.id.tvDc);
                }

            }
        }
