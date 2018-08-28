package com.example.batch31.androidminiproject;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.List;

public class ContactListAdapter extends RecyclerView.Adapter<ContactListAdapter.ContactViewHolder> {

    private OnContactItemClick onContactItemClick;
    private final LayoutInflater cInflater;
    private List<Contact> cContacts;

    public class ContactViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private final TextView contactItemView;

        private ContactViewHolder(View itemView) {
            super(itemView);
            contactItemView = itemView.findViewById(R.id.textView);
        }

        @Override
        public void onClick(View view) {
            onContactItemClick.onContactClick(getAdapterPosition());
        }
    }

    public interface OnContactItemClick{
        void onContactClick(int pos);
    }

    ContactListAdapter(List<Contact> list, Context context){
        this.cInflater = LayoutInflater.from(context);
        this.cContacts = list;
        this.onContactItemClick = (OnContactItemClick) context;
    }


    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View itemView = cInflater.inflate(R.layout.recyclerview_item,parent,false);
        return new ContactViewHolder(itemView);
    }

    public void onBindViewHolder(ContactViewHolder holder, int position){
        if (cContacts != null){
            Contact current = cContacts.get(position);
            holder.contactItemView.setText(current.getFirstName());
        } else {
            holder.contactItemView.setText("No Contact");
        }
    }

    void setcContacts(List<Contact> contacts){
        cContacts = contacts;
        notifyDataSetChanged();
    }


    public int getItemCount(){
        if (cContacts !=null){
            return cContacts.size();
        } else return 0;
    }
}
