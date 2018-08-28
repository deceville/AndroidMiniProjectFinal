package com.example.batch31.androidminiproject;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.List;

public class ContactListAdapter extends RecyclerView.Adapter<ContactListAdapter.ContactViewHolder> {

    private OnContactItemClick OnContactItemClick;
    private String name = null;
    private List<Contact> contact;

    class ContactViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private final TextView wordItemView;

        private ContactViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            wordItemView = itemView.findViewById(R.id.textView);
        }

        @Override
        public void onClick(View view) {
            OnContactItemClick.onContactClick(getAdapterPosition());
        }
    }

    private final LayoutInflater mInflater;
    private List<Contact> contacts; // Cached copy of words

    ContactListAdapter(List<Contact>contact, Context context) {
        mInflater = LayoutInflater.from(context);
        this.contact = contact;
        this.OnContactItemClick = (OnContactItemClick) context;
    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new ContactViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ContactViewHolder holder, int position) {
        Contact current = contacts.get(position);
        holder.wordItemView.setText(current.getFirstName());
        holder.wordItemView.setText(current.getLastName());
        holder.wordItemView.setText(current.getContactNumber());
        holder.wordItemView.setText(current.getContactEmail());
    }

    void setcContacts(List<Contact> contact){
        contacts = contact;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (contacts != null)
            return contacts.size();
        else return 0;
    }


    public interface OnContactItemClick{
        void onContactClick(int pos);
    }
}
