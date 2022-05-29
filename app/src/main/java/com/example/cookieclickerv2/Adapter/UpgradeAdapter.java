package com.example.cookieclickerv2.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cookieclickerv2.Activity.GameActivity;
import com.example.cookieclickerv2.Converter.MoneyConverter;
import com.example.cookieclickerv2.Model.Upgrade;
import com.example.cookieclickerv2.R;
import com.example.cookieclickerv2.Storage.Storage;
import com.google.android.material.progressindicator.LinearProgressIndicator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

public class UpgradeAdapter extends RecyclerView.Adapter<UpgradeAdapter.ViewHolder> {

    ArrayList<Upgrade> upgradeArrayList;
    ArrayList<Integer> levelPoint;
    Context context;
    Storage storage;
    GameActivity gameActivity;

    public UpgradeAdapter(ArrayList<Upgrade> upgradeArrayList, Context context, GameActivity gameActivity) {
        this.upgradeArrayList = upgradeArrayList;
        this.context = context;
        this.gameActivity = gameActivity;

        storage = new Storage(context);
    }

    @NonNull
    @Override
    public UpgradeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_list_upgrade, parent, false);
        return new UpgradeAdapter.ViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        System.out.println(storage);
        Upgrade upgrade = upgradeArrayList.get(position);

        levelPoint = makeLevelPoint(upgrade.getMaxLevel());

        int level = storage.getItemLevel(upgrade.getId(), upgrade.isFirst());
        int maxLevel = levelPoint.get(0);
        int minLevel = 0;
        int i = 1;
        while (maxLevel <= level) {
            maxLevel = levelPoint.get(i);
            minLevel = levelPoint.get(i - 1) - 1;
            i++;
        }

        holder.itemTitle.setText(upgrade.getTitle());
        holder.itemImage.setImageResource(upgrade.getImage());
        holder.itemUpgradeButton.setText(new MoneyConverter(storage.getItemPrice(upgrade.getId(), upgrade.getStartPrice())).getAllMoney());
        holder.itemAddMoney.setText(new MoneyConverter(storage.getItemAddMoney(upgrade.getId(), upgrade.getAddMoney())).getAllMoney());
        holder.itemLevel.setText("lvl: " + storage.getItemLevel(upgrade.getId(), upgrade.isFirst()) + " / " + maxLevel);
        holder.itemLevelView.setMax(maxLevel);
        holder.itemLevelView.setMin(minLevel);
        holder.itemLevelView.setProgress(storage.getItemLevel(upgrade.getId(), upgrade.isFirst()));


        holder.itemUpgradeButton.setOnClickListener(view -> {
            if (new BigDecimal(storage.getMoney()).compareTo(new BigDecimal(storage.getItemPrice(upgrade.getId(), upgrade.getStartPrice()))) >= 0) {
                storage.removeMoney(storage.getItemPrice(upgrade.getId(), upgrade.getStartPrice()));
                storage.setItemLevel(upgrade.getId(), upgrade.isFirst());
                storage.setItemPrice(upgrade.getId(), upgrade.getStartPrice());
                storage.setAddMoney(storage.getItemAddMoney(upgrade.getId(), upgrade.getAddMoney()));

                int level1 = storage.getItemLevel(upgrade.getId(), upgrade.isFirst());
                int maxLevel1 = levelPoint.get(0);
                int minLevel1 = 0;
                int i1 = 1;
                while (maxLevel1 <= level1) {
                    maxLevel1 = levelPoint.get(i1);
                    minLevel1 = levelPoint.get(i1 - 1) - 1;
                    i1++;
                }
                holder.itemLevel.setText("lvl: " + storage.getItemLevel(upgrade.getId(), upgrade.isFirst()) + " / " + maxLevel1);
                holder.itemLevelView.setMax(maxLevel1);
                holder.itemLevelView.setMin(minLevel1);
                holder.itemLevelView.setProgress(storage.getItemLevel(upgrade.getId(), upgrade.isFirst()));

                if (level1 == minLevel1 + 1 && level1 != 1) {
                    storage.setItemAddMoneyBonus(upgrade.getId(), upgrade.getAddMoney());
                } else {
                    storage.setItemAddMoney(upgrade.getId(), upgrade.getAddMoney());
                }

                gameActivity.updateAllScreen();
                holder.itemUpgradeButton.setText(new MoneyConverter(storage.getItemPrice(upgrade.getId(), upgrade.getStartPrice())).getAllMoney());

                holder.itemAddMoney.setText(new MoneyConverter(storage.getItemAddMoney(upgrade.getId(), upgrade.getAddMoney())).getAllMoney());
            }
        });
    }

    @Override
    public int getItemCount() {
        return upgradeArrayList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView itemImage;
        TextView itemTitle, itemLevel, itemAddMoney;
        LinearProgressIndicator itemLevelView;
        Button itemUpgradeButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemImage = itemView.findViewById(R.id.itemImage);
            itemTitle = itemView.findViewById(R.id.itemTitle);
            itemLevel = itemView.findViewById(R.id.itemLevel);
            itemAddMoney = itemView.findViewById(R.id.itemMoneyAdd);
            itemLevelView = itemView.findViewById(R.id.itemLevelView);
            itemUpgradeButton = itemView.findViewById(R.id.itemUpgradeButton);
        }
    }

    private ArrayList<Integer> makeLevelPoint(int maxLevel) {
        ArrayList<Integer> levelPoint = new ArrayList<>();

        levelPoint.add(10);
        levelPoint.add(25);
        levelPoint.add(50);
        levelPoint.add(75);
        levelPoint.add(100);
        levelPoint.add(125);
        levelPoint.add(150);
        levelPoint.add(175);
        levelPoint.add(200);
        levelPoint.add(250);
        levelPoint.add(300);
        levelPoint.add(350);
        levelPoint.add(400);
        levelPoint.add(450);
        levelPoint.add(500);
        levelPoint.add(600);
        levelPoint.add(700);
        levelPoint.add(800);
        levelPoint.add(900);
        levelPoint.add(1000);

        for (int i = 1000; i < maxLevel; i += 250) {
            levelPoint.add(i);
        }

        return levelPoint;
    }
}
