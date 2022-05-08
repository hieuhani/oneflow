package vn.periscope.cms.ports.contenttypefield.models
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed class ContentTypeFieldExtraData

enum class ContentTypeTextEditorMode {
    WYSIWYG,
    PLAIN_TEXT,
}

@Serializable
@SerialName("TEXT")
class ContentTypeTextFieldExtraData(
    val editorMode: ContentTypeTextEditorMode,
) : ContentTypeFieldExtraData()