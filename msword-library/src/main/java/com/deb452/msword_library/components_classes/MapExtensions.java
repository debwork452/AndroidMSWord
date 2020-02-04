package com.deb452.msword_library.components_classes;

import android.app.Activity;
import android.text.Editable;
import android.view.View;
import android.widget.ImageView;

import com.deb452.msword_library.EditorComponent;
import com.deb452.msword_library.EditorCore;
import com.deb452.msword_library.R;
import com.deb452.msword_library.utilities.Utilities;
import com.deb452.msword_library.models.EditorContent;
import com.deb452.msword_library.models.EditorControl;
import com.deb452.msword_library.models.EditorType;
import com.deb452.msword_library.models.Node;
import com.deb452.msword_library.models.RenderType;

import org.jsoup.nodes.Element;

public class MapExtensions extends EditorComponent {
    EditorCore editorCore;
    private int mapExtensionTemplate = R.layout.tmpl_image_view;

    @Override
    public Node getContent(View view) {
        Node node = getNodeInstance(view);
        EditorControl mapTag = (EditorControl) view.getTag();
        Editable desc = ((CustomEditText) view.findViewById(R.id.desc)).getText();
        node.content.add(mapTag.Cords);
        node.content.add(desc.length() > 0 ? desc.toString() : "");
        return node;
    }

    @Override
    public String getContentAsHTML(Node node, EditorContent content) {
        return componentsWrapper.getHtmlExtensions().getTemplateHtml(node.type).replace("{{$content}}",
                componentsWrapper.getMapExtensions().getCordsAsUri(node.content.get(0))).replace("{{$desc}}", node.content.get(1));
    }

    @Override
    public void renderEditorFromState(Node node, EditorContent content) {
        insertMap(node.content.get(0), node.content.get(1), true);
    }

    @Override
    public Node buildNodeFromHTML(Element element) {
        return null;
    }

    @Override
    public void init(ComponentsWrapper componentsWrapper) {
        this.componentsWrapper = componentsWrapper;
    }

    public MapExtensions(EditorCore editorCore) {
        super(editorCore);
        this.editorCore = editorCore;
    }

    public void setMapViewTemplate(int drawable) {
        this.mapExtensionTemplate = drawable;
    }


    public String getMapStaticImgUri(String cords, int width) {
        StringBuilder builder = new StringBuilder();
        builder.append("http://maps.google.com/maps/api/staticmap?");
        builder.append("size=" + String.valueOf(width) + "x400&zoom=15&sensor=true&markers=" + cords);
        return builder.toString();
    }

    public void insertMap(String cords, String desc, boolean insertEditText) {
//        String image="http://maps.googleapis.com/maps/api/staticmap?center=43.137022,13.067162&zoom=16&size=600x400&maptype=roadmap&sensor=true&markers=color:blue|43.137022,13.067162";
        String[] x = cords.split(",");
        String lat = x[0];
        String lng = x[1];
        int[] size = Utilities.INSTANCE.getScreenDimension(editorCore.getContext());
        int width = size[0];
//        ImageView imageView = new ImageView(context);
//        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 400);
//        params.bottomMargin=12;
//        imageView.setLayoutParams(params);
//        parentView.addView(imageView);
//        Picasso.with(this.context).load(builder.toString()).into(imageView);

        final View childLayout = ((Activity) this.editorCore.getContext()).getLayoutInflater().inflate(this.mapExtensionTemplate, null);
        ImageView imageView = childLayout.findViewById(R.id.imageView);
        componentsWrapper.getImageExtensions().loadImageUsingLib(getMapStaticImgUri(String.valueOf(lat) + "," + String.valueOf(lng), width), imageView);

        /**
         * description, if render mode, set the description and disable it
         */
        CustomEditText editText = childLayout.findViewById(R.id.desc);
        if (editorCore.getRenderType() == RenderType.Renderer) {
            editText.setText(desc);
            editText.setEnabled(false);
        }
        /*
         *  remove button
         */

        final View btn = childLayout.findViewById(R.id.btn_remove);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn.setVisibility(View.VISIBLE);
            }
        });
        imageView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                btn.setVisibility(hasFocus ? View.VISIBLE : View.INVISIBLE);
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editorCore.getParentView().removeView(childLayout);
            }
        });
        EditorControl control = editorCore.createTag(EditorType.map);
        control.Cords = cords;
        childLayout.setTag(control);
        int Index = editorCore.determineIndex(EditorType.map);
        editorCore.getParentView().addView(childLayout, Index);
        if (insertEditText) {
            componentsWrapper.getInputExtensions().insertEditText(Index + 1, null, null);
        }
    }

    public void loadMapActivity() {
        // Intent intent=new Intent(this.editorCore.getContext(), MapsActivity.class);
        // ((Activity) this.editorCore.getContext()).startActivityForResult(intent, 123);
    }

    public CharSequence getCordsAsUri(String s) {
        return getMapStaticImgUri(s, 800);
    }
}
