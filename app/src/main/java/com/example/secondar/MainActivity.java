package com.example.secondar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.ar.core.Anchor;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.TransformableNode;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private ArFragment arFragment;
    private ArrayList<Integer> imagesPath = new ArrayList<Integer>();
    private ArrayList<String> namesPath = new ArrayList<>();
    private ArrayList<String> modelNames = new ArrayList<>();
    AnchorNode anchorNode;
    private Button btnRemove;
    private ArrayList<AnchorNode> allNodes= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        arFragment = (ArFragment)getSupportFragmentManager().findFragmentById(R.id.fragment);
        btnRemove = (Button)findViewById(R.id.remove);
        getImages();

        arFragment.setOnTapArPlaneListener((hitResult, plane, motionEvent) -> {

            Anchor anchor = hitResult.createAnchor();

            ModelRenderable.builder()
                    .setSource(this,Uri.parse(Common.model))
                    .build()
                    .thenAccept(modelRenderable -> addModelToScene(anchor,modelRenderable));

        });


        btnRemove.setOnClickListener(view -> removeAnchorNode());
    }

    private void getImages() {

        imagesPath.add(R.drawable.chesterfieldsofa);
        imagesPath.add(R.drawable.banquet_table_with_catering);
        imagesPath.add(R.drawable.flowers_from_team);

        imagesPath.add(R.drawable.luminous_stage);
        imagesPath.add(R.drawable.main_stage_water_feature);

        imagesPath.add(R.drawable.mm2);
        imagesPath.add(R.drawable.single_stage);
        imagesPath.add(R.drawable.sofa_single);
        imagesPath.add(R.drawable.stage);
        imagesPath.add(R.drawable.stage_4);
        imagesPath.add(R.drawable.stage_5);
        imagesPath.add(R.drawable.stage_10);

        imagesPath.add(R.drawable.stage_light);
        imagesPath.add(R.drawable.stage_panggung);
        imagesPath.add(R.drawable.victorian_lounge_sofa);
        imagesPath.add(R.drawable.vintage_chair);
        imagesPath.add(R.drawable.vintage_coffee_table_70s_freebie);
        imagesPath.add(R.drawable.wedding_cake_couple_01);
        namesPath.add("Chester Field Sofa");
        namesPath.add("Banquet Table with Catering");
        namesPath.add("Flowers from team");

        namesPath.add("Luminous stage");
        namesPath.add("Main stage water feature");
        namesPath.add("mm2");
        namesPath.add("Single stage");
        namesPath.add("Sofa single");
        namesPath.add("Stage");
        namesPath.add("Stage 4");
        namesPath.add("Stage 5");
        namesPath.add("Stage 10");

        namesPath.add("Stage light");
        namesPath.add("Stage panggung");
        namesPath.add("Victorian lounge sofa");
        namesPath.add("Vintage chair");
        namesPath.add("Vintage coffee table 70s freebie");
        namesPath.add("Wedding cake couple 01");
        modelNames.add("chesterfieldsofa.sfb");
        modelNames.add("banquet_table_with_catering.sfb");
        modelNames.add("flowers_from_team.sfb");
        modelNames.add("luminous_stage.sfb");

        modelNames.add("main_stage_water_feature.sfb");
        modelNames.add("mm2.sfb");
        modelNames.add("single_stage.sfb");
        modelNames.add("sofa_single.sfb");
        modelNames.add("stage.sfb");
        modelNames.add("stage_4.sfb");
        modelNames.add("stage_5.sfb");
        modelNames.add("stage_10.sfb");

        modelNames.add("stage_light.sfb");
        modelNames.add("stage_panggung.sfb");
        modelNames.add("victorian_lounge_sofa.sfb");
        modelNames.add("vintage_chair.sfb");
        modelNames.add("vintage_coffee_table_70s_freebie.sfb");
        modelNames.add("wedding_cake_couple_01.sfb");

        initaiteRecyclerview();
    }

    private void initaiteRecyclerview() {

        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerviewAdapter adapter = new RecyclerviewAdapter(this,namesPath,imagesPath,modelNames);
        recyclerView.setAdapter(adapter);

    }

    private void addModelToScene(Anchor anchor, ModelRenderable modelRenderable) {

        anchorNode = new AnchorNode(anchor);
        allNodes.add(anchorNode);
        TransformableNode node = new TransformableNode(arFragment.getTransformationSystem());
        node.setParent(anchorNode);
        node.setRenderable(modelRenderable);
        arFragment.getArSceneView().getScene().addChild(allNodes.get(allNodes.size()-1));
        node.select();

    }

    public void removeAnchorNode(AnchorNode nodeToremove) {
        if (nodeToremove != null) {
            Log.d("A", "removeAnchorNode: "+nodeToremove.getName().toString());
            arFragment.getArSceneView().getScene().removeChild(nodeToremove);
            nodeToremove.getAnchor().detach();
            nodeToremove.setParent(null);
            nodeToremove = null;
        }
    }

    public void removeAnchorNode() {
        try {
            Log.d("Abbb", "TotalSize: "+allNodes.size());

            AnchorNode nodeToremove= allNodes.get(allNodes.size()-1);
            if (nodeToremove != null) {
                arFragment.getArSceneView().getScene();
                Log.d("Abbb", "removeAnchorNode: "+nodeToremove.toString());
                arFragment.getArSceneView().getScene().removeChild(nodeToremove);
                nodeToremove.getAnchor().detach();
                nodeToremove.setParent(null);
                nodeToremove = null;
                allNodes.remove(allNodes.size()-1);
            }

        }catch (Exception e){
            Log.d("Abbb", "removeAnchorNode exception: "+ e.toString());
        }

    }
}