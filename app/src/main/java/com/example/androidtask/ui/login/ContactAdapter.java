package com.example.androidtask.ui.login;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidtask.R;
import com.example.androidtask.UserEntity.Contact;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {

    private List<Contact> contactList;
    private Contact selectedContact;
    private Boolean clickMore = false;
    private boolean showName = true;
    private boolean showPhoneNumber = true;
    private boolean showEmail = true;
    private boolean showAdress = true;

    public void setShowEmail(boolean showEmail) {
        this.showEmail = showEmail;
    }
    public void setShowAddress(boolean showAdress) {
        this.showAdress = showAdress;
    }

    public void setShowName(boolean showName) {
        this.showName = showName;
    }

    public void setShowPhoneNumber(boolean showPhoneNumber) {
        this.showPhoneNumber = showPhoneNumber;
    }

    public void setContacts(List<Contact> contacts) {
        this.contactList = contacts;
        notifyDataSetChanged();
    }


    public Contact getSelectedContact() {
        return selectedContact;
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_contact, parent, false);
        return new ContactViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        if (contactList != null) {
            Contact currentContact = contactList.get(position);
            holder.bind(currentContact);
        }
        if (showName) {
            holder.nameTextView.setVisibility(View.VISIBLE);
        } else {
            holder.nameTextView.setVisibility(View.GONE);
        }
        if (showPhoneNumber) {
            holder.phoneNumberTextView.setVisibility(View.VISIBLE);
        } else {
            holder.phoneNumberTextView.setVisibility(View.GONE);
        }

        if (showEmail) {
            holder.textViewEmail.setVisibility(View.VISIBLE);
        } else {
            holder.textViewEmail.setVisibility(View.GONE);
        }
        if (showAdress) {
            holder.textViewAddress.setVisibility(View.VISIBLE);
        } else {
            holder.textViewAddress.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return (contactList != null) ? contactList.size() : 0;
    }

    class ContactViewHolder extends RecyclerView.ViewHolder {
        private TextView nameTextView;
        private TextView phoneNumberTextView;
        private TextView textViewEmail;
        private TextView textViewAddress;



        ContactViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.textViewName);
            phoneNumberTextView = itemView.findViewById(R.id.textViewPhoneNumber);
            textViewEmail = itemView.findViewById(R.id.textViewEmail);
            textViewAddress = itemView.findViewById(R.id.textViewAddress);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        selectedContact = contactList.get(position);
                        notifyDataSetChanged(); // Refresh the UI to reflect the selection
                    }
                }
            });
        }


        void bind(Contact contact) {
            nameTextView.setText(contact.getName());
            phoneNumberTextView.setText(contact.getPhoneNumber());
            textViewEmail.setText(contact.getEmail());
            textViewAddress.setText(contact.getAddress());

        }
    }
}
