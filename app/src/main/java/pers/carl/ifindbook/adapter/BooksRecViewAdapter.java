package pers.carl.ifindbook.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

import pers.carl.ifindbook.BookDetailActivity;
import pers.carl.ifindbook.R;
import pers.carl.ifindbook.pojo.Book;
import pers.carl.ifindbook.utils.DBUtils;

public class BooksRecViewAdapter extends RecyclerView.Adapter<BooksRecViewAdapter.ViewHolder> {

    private ArrayList<Book> books = new ArrayList<>();
    private Context mcontext;

    public BooksRecViewAdapter(Context context) {
        this.mcontext = context;
    }

    public BooksRecViewAdapter() {
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_book, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.v("log: ", "onBindViewHolder called");
        holder.bookName.setText(books.get(position).getName());
        holder.bookAuthor.setText(books.get(position).getAuthor());
        holder.longDesc.setText(books.get(position).getLongDesc());
        holder.shortDesc.setText(books.get(position).getShortDesc());

        Glide.with(mcontext)
                .asBitmap()
                .load(books.get(position).getImgUrl())
                .into(holder.imgBook);

//        holder.imgBook.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                Snackbar.make(holder.parent, books.get(position).getId() + " clicked. ", Snackbar.LENGTH_SHORT)
////                        .show();
//                if (books.get(position).isExpanded()) {
//                    holder.longDesc.setVisibility(View.GONE);
//                    books.get(position).setExpanded(false);
//                } else {
//                    holder.longDesc.setVisibility(View.VISIBLE);
//                    books.get(position).setExpanded(true);
//                }
//
//            }
//        });

        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mcontext, BookDetailActivity.class);

                if (DBUtils.getUser().getId() != 0) {
                    intent.putExtra("bookId", books.get(position).getId());
                    mcontext.startActivity(intent);
                } else {
                    try {
                        ObjectMapper mapper = new ObjectMapper();
                        String string = mapper.writeValueAsString(books.get(position));
                        intent.putExtra("objBook", string);
                        mcontext.startActivity(intent);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private CardView parent;
        private ImageView imgBook;
        private TextView bookName, bookAuthor, longDesc, shortDesc;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            parent = itemView.findViewById(R.id.parent);
            imgBook = itemView.findViewById(R.id.imgBook);
            bookName = itemView.findViewById(R.id.bookName);
            bookAuthor = itemView.findViewById(R.id.bookAuthor);
            longDesc = itemView.findViewById(R.id.txtLongDesc);
            shortDesc = itemView.findViewById(R.id.txtShortDesc);

        }
    }

}
