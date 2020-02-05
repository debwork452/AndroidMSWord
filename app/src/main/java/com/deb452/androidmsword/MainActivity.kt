package com.deb452.androidmsword

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.deb452.msword_library.Editor
import com.deb452.msword_library.EditorListener
import com.deb452.msword_library.models.EditorTextStyle
import kotlinx.android.synthetic.main.toolbar_editor.*
import top.defaults.colorpicker.ColorPickerPopup
import top.defaults.colorpicker.ColorPickerPopup.ColorPickerObserver
import java.io.IOException
import java.util.*

class MainActivity : AppCompatActivity() {
    var editor: Editor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        editor = findViewById(R.id.editor)
        setUpEditor()
    }

    private fun setUpEditor() {
        findViewById<Button>(R.id.action_h1).setOnClickListener(View.OnClickListener {
            editor!!.updateTextStyle(
                EditorTextStyle.H1
            )
        })
        findViewById<Button>(R.id.action_h2).setOnClickListener(View.OnClickListener {
            editor!!.updateTextStyle(
                EditorTextStyle.H2
            )
        })

        findViewById<Button>(R.id.action_h3).setOnClickListener(View.OnClickListener {
            editor!!.updateTextStyle(
                EditorTextStyle.H3
            )
        })
        findViewById<ImageButton>(R.id.action_bold).setOnClickListener(View.OnClickListener {
            editor!!.updateTextStyle(
                EditorTextStyle.BOLD
            )
        })
        findViewById<ImageButton>(R.id.action_Italic).setOnClickListener(View.OnClickListener {
            editor!!.updateTextStyle(
                EditorTextStyle.ITALIC
            )
        })
        findViewById<ImageButton>(R.id.action_indent).setOnClickListener(View.OnClickListener {
            editor!!.updateTextStyle(
                EditorTextStyle.INDENT
            )
        })
        findViewById<ImageButton>(R.id.action_blockquote).setOnClickListener(View.OnClickListener {
            editor!!.updateTextStyle(
                EditorTextStyle.BLOCKQUOTE
            )
        })
        findViewById<ImageButton>(R.id.action_outdent).setOnClickListener(View.OnClickListener {
            editor!!.updateTextStyle(
                EditorTextStyle.OUTDENT
            )
        })
        findViewById<ImageButton>(R.id.action_bulleted).setOnClickListener(View.OnClickListener {
            editor!!.insertList(
                false
            )
        })
        findViewById<ImageButton>(R.id.action_unordered_numbered).setOnClickListener(View.OnClickListener {
            editor!!.insertList(
                true
            )
        })
        findViewById<ImageButton>(R.id.action_hr).setOnClickListener(View.OnClickListener { editor!!.insertDivider() })
        findViewById<ImageButton>(R.id.action_color).setOnClickListener(View.OnClickListener {
            ColorPickerPopup.Builder(this)
                .initialColor(Color.RED) // Set initial color
                .enableAlpha(true) // Enable alpha slider or not
                .okTitle("Choose")
                .cancelTitle("Cancel")
                .showIndicator(true)
                .showValue(false)
                .build()
                .show(findViewById(android.R.id.content), object : ColorPickerObserver {
                    override fun onColorPicked(color: Int) {
                       /* Toast.makeText(
                            this@MainActivity,
                            "picked" + colorHex(color),
                            Toast.LENGTH_LONG
                        ).show()*/
                        editor!!.updateTextColor(colorHex(color))
                    }

                    override fun onColor(color: Int, fromUser: Boolean) {}
                })
        })
        findViewById<ImageButton>(R.id.action_insert_image).setOnClickListener(View.OnClickListener { editor!!.openImagePicker() })
        findViewById<ImageButton>(R.id.action_insert_link).setOnClickListener(View.OnClickListener { editor!!.insertLink() })
        findViewById<ImageButton>(R.id.action_erase).setOnClickListener(View.OnClickListener { editor!!.clearAllContents() })
        //editor.dividerBackground=R.drawable.divider_background_dark;
        //editor.setFontFace(R.string.fontFamily__serif);
        val headingTypeface: Map<Int, String> = getHeadingTypeface()!!
        val contentTypeface: Map<Int, String> = getContentface()!!
        editor!!.headingTypeface = headingTypeface
        editor!!.contentTypeface = contentTypeface
        editor!!.setDividerLayout(R.layout.tmpl_divider_layout)
        editor!!.setEditorImageLayout(R.layout.tmpl_image_view)
        editor!!.setListItemLayout(R.layout.tmpl_list_item)
        //editor.setNormalTextSize(10);
        //editor.setEditorTextColor("#FF3333");
        //editor.StartEditor();
        editor!!.editorListener = object : EditorListener {
            override fun onTextChanged(
                editText: EditText?,
                text: Editable?
            ) { // Toast.makeText(EditorTestActivity.this, text, Toast.LENGTH_SHORT).show();
            }
            override fun onUpload(image: Bitmap?, uuid: String?) {
//                Toast.makeText(this@MainActivity, uuid, Toast.LENGTH_LONG).show()

                editor!!.onImageUploadComplete(
                    "http://www.videogamesblogger.com/wp-content/uploads/2015/08/metal-gear-solid-5-the-phantom-pain-cheats-640x325.jpg",
                    uuid
                )
                // editor.onImageUploadFailed(uuid);
            }

            override fun onRenderMacro(
                name: String?,
                props: Map<String?, Any?>?,
                index: Int
            ): View? {
                return layoutInflater.inflate(R.layout.layout_authored_by, null)
            }
        }

        val text =
            "<h1 data-tag=\"input\" style=\"color:#c00000;\"><span style=\"color:#C00000;\">textline 1 a great time and I will branch office is closed on Sundays</span></h1><hr data-tag=\"hr\"/><p data-tag=\"input\" style=\"color:#000000;\">the only one that you have received the stream free and open minded person to discuss a business opportunity to discuss my background.</p><div data-tag=\"img\"><img src=\"http://www.videogamesblogger.com/wp-content/uploads/2015/08/metal-gear-solid-5-the-phantom-pain-cheats-640x325.jpg\" /><p data-tag=\"img-sub\" style=\"color:#FF0000;\" class=\"editor-image-subtitle\"><b>it is a great weekend and we will have the same to me that the same a great time</b></p></div><p data-tag=\"input\" style=\"color:#000000;\">I have a place where I have a great time and I will branch manager state to boast a new job in a few weeks and we can host or domain to get to know.</p><div data-tag=\"img\"><img src=\"http://www.videogamesblogger.com/wp-content/uploads/2015/08/metal-gear-solid-5-the-phantom-pain-cheats-640x325.jpg\" /><p data-tag=\"img-sub\" style=\"color:#5E5E5E;\" class=\"editor-image-subtitle\">the stream of water in a few weeks and we can host in the stream free and no ippo</p></div><p data-tag=\"input\" style=\"color:#000000;\">it is that I can get it done today will online at location and I am not a big difference to me so that we are headed <a href=\"www.google.com\">www.google.com</a> it was the only way I.</p><blockquote data-tag=\"input\" style=\"color:#000000;\">I have to do the negotiation and a half years old story and I am looking forward in a few days.</blockquote><p data-tag=\"input\" style=\"color:#000000;\">it is not a good day to get the latest version to blame it to the product the.</p><ol data-tag=\"ol\"><li data-tag=\"list-item-ol\"><span style=\"color:#000000;\">it is that I can send me your email to you and I am not able a great time and consideration I have to do the needful.</span></li><li data-tag=\"list-item-ol\"><span style=\"color:#000000;\">I have to do the needful and send to me and</span></li><li data-tag=\"list-item-ol\"><span style=\"color:#000000;\">I will be a while ago to a great weekend a great time with the same.</span></li></ol><p data-tag=\"input\" style=\"color:#000000;\">it was u can do to make an offer for a good day I u u have been working with a new job to the stream free and no.</p><p data-tag=\"input\" style=\"color:#000000;\">it was u disgraced our new home in time to get the chance I could not find a good idea for you have a great.</p><p data-tag=\"input\" style=\"color:#000000;\">I have to do a lot to do the same a great time and I have a great.</p><p data-tag=\"input\" style=\"color:#000000;\"></p>"


        editor!!.render(editor!!.contentAsHTML)
        btnRender.setOnClickListener(View.OnClickListener {
            //Retrieve the content as serialized, you could also say getContentAsHTML();
            val texts = editor!!.contentAsSerialized
            editor!!.contentAsHTML
            val intent = Intent(applicationContext, RenderTestActivity::class.java)
            intent.putExtra("content", texts)
            startActivity(intent)
        })
    }

    private fun colorHex(color: Int): String? {
        val r = Color.red(color)
        val g = Color.green(color)
        val b = Color.blue(color)
        return String.format(Locale.getDefault(), "#%02X%02X%02X", r, g, b)
    }

    private fun setGhost(button: Button) {
        val radius = 4
        val background = GradientDrawable()
        background.shape = GradientDrawable.RECTANGLE
        background.setStroke(4, Color.WHITE)
        background.cornerRadius = radius.toFloat()
        button.setBackgroundDrawable(background)
    }

    @SuppressLint("MissingSuperCall")
    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        if (requestCode == editor!!.PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK &&
            data != null && data.data != null) {
            val uri = data.data
            try {
                val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, uri)
                // Log.d(TAG, String.valueOf(bitmap));
                editor!!.insertImage(bitmap)
            } catch (e: IOException) {
                Toast.makeText(applicationContext, e.message, Toast.LENGTH_SHORT).show()
                e.printStackTrace()
            }
        } else if (resultCode == Activity.RESULT_CANCELED) { //Write your code if there's no result
            Toast.makeText(applicationContext, "Cancelled", Toast.LENGTH_SHORT).show()
            // editor.RestoreState();
        }
    }

    override fun onBackPressed() {
        AlertDialog.Builder(this)
            .setTitle("Exit Editor?")
            .setMessage("Are you sure you want to exit the editor?")
            .setPositiveButton(
                "Yes"
            ) { dialog, which -> finish() }
            .setNegativeButton("No", null)
            .show()
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        setGhost(findViewById(R.id.btnRender))
    }

    fun getHeadingTypeface(): Map<Int, String>? {
        val typefaceMap: MutableMap<Int, String> =
            HashMap()
        typefaceMap[Typeface.NORMAL] = "fonts/GreycliffCF-Bold.ttf"
        typefaceMap[Typeface.BOLD] = "fonts/GreycliffCF-Heavy.ttf"
        typefaceMap[Typeface.ITALIC] = "fonts/GreycliffCF-Heavy.ttf"
        typefaceMap[Typeface.BOLD_ITALIC] = "fonts/GreycliffCF-Bold.ttf"
        return typefaceMap
    }

    fun getContentface(): Map<Int, String>? {
        val typefaceMap: MutableMap<Int, String> =
            HashMap()
        typefaceMap[Typeface.NORMAL] = "fonts/Lato-Medium.ttf"
        typefaceMap[Typeface.BOLD] = "fonts/Lato-Bold.ttf"
        typefaceMap[Typeface.ITALIC] = "fonts/Lato-MediumItalic.ttf"
        typefaceMap[Typeface.BOLD_ITALIC] = "fonts/Lato-BoldItalic.ttf"
        return typefaceMap
    }
}
