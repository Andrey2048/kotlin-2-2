class Likes(
    count: Int = 0,//число пользователей, которым понравилась запись
    val userLikes: Boolean = false, //наличие отметки «Мне нравится» от текущего пользователя
    val canLike: Boolean = false, //может ли текущий пользователь поставить отметку «Мне нравится»
    val canPublish: Boolean = false //может ли текущий пользователь сделать репост записи
) {
    var count: Int = count
        set(value) {
            if (value > 0) field = value
        }
}

class Comments(
    count: Int = 0, // количество комментариев
    val canPost: Boolean = false, // может ли текущий пользователь комментировать запись
    val groupsCanPost: Boolean = false, // могут ли сообщества комментировать запись
    val canClose: Boolean = false, // может ли текущий пользователь закрыть комментарии к записи
    val canOpen: Boolean = false // может ли текущий пользователь открыть комментарии к записи
) {
    var count: Int = count
        set(value) {
            if (value > 0) field = value
        }
}


class Copyright(
    val id: Int = 0,
    val link: String = "",
    val name: String = "",
    val type: String = ""
)

class Reposts(
    count: Int = 0, // количество репостов
    val userReposted: Boolean = false //наличие репоста от текущего пользователя
) {
    var count: Int = count
        set(value) {
            if (value > 0) field = value
        }
}

class Views(count: Int = 0) {
    var count: Int = count
        set(value) {
            if (value > 0) field = value
        }
}

class PostSource(
    val type: String = "", //тип источника
    val platform: String = "", //название платформы
    val data: String = "", //тип действия
    val url: String = ""
)

class Geo(
    val type: String = "", //тип места
    val coordinates: String = "", //координаты места
    val place: String = "" // описание места
)

class Donut(
    val isDonut: Boolean = false,
    val paidDuration: Int = 1000,
    val placeHolder: String = "",
    val canPublishFreeCopy: Boolean = false,
    val editMode: String = ""
)

interface Attachments {
    val type: String
}

data class PhotoAttachment(val photo: Photo) : Attachments {
    override val type = "photo"
}

data class Photo(
    val id: Int,
    val albumId: Int,
    val ownerId: Int,
    val text: String,
    val date: Int,
    val sizes: Array<TypeOfPhoto>,
    val width: Int,
    val height: Int
)

data class TypeOfPhoto(
    val type: String,
    val url: String,
    val width: Int,
    val height: Int
)

data class AlbumPhotoAttachment(val albumPhoto: AlbumPhoto) : Attachments {
    override val type = "album"
}

data class AlbumPhoto(
    val id: Int,
    val thumb: Photo,
    val ownerId: Int,
    val title: String,
    val description: String,
    val created: Int,
    val updated: Int,
    val size: Int
)

data class AudioAttachment(val audio: Audio) : Attachments {
    override val type = "audio"
}

data class Audio(
    val id: Int,
    val ownerId: Int,
    val artist: String,
    val title: String,
    val duration: Int,
    val url: String,
    val lyricsId: Int,
    val albumId: Int,
    val genreId: Int,
    val date: Int,
    val noSearch: Boolean,
    val isHq: Boolean,
)

data class LinkAttachment(val link: Link) : Attachments {
    override val type = "link"
}

data class Link(
    val url: String,
    val title: String,
    val caption: String,
    val description: String,
    val photo: Photo,
    val product: Product,
    val button: Button,
    val previewPage: String,
    val previewUrl: String
)

data class Product(
    val accessKey: String,
    val price: Int
)

data class Button(
    val title: String,
    val actionType: String,
    val actionUrl: String
)

data class NoteAttachment(val note: Note) : Attachments {
    override val type = "note"
}

data class Note(
    val id: Int,
    val ownerId: Int,
    val title: String,
    val text: String,
    val date: Int,
    val comments: Int,
    val readComments: Int,
    val viewUrl: String,
    val privacyView: String,
    val canComment: Boolean,
    val description: String,
    val textWiki: String
)

data class Comment(
    val id: Int = 20, //Идентификатор комментария
    val fromId: Int = 2, //Идентификатор автора комментария
    val date: Int = 1590075400, //Дата создания комментария в формате Unixtime
    val text: String = "It's my first comment", //Текст комментария
    val donut: Donut? = null, //Информация о VK Donut
    val replyToUser: Int = 0, //Идентификатор пользователя или сообщества, в ответ которому оставлен текущий комментарий (если применимо)
    val replyToComment: Int = 0, //Идентификатор комментария, в ответ на который оставлен текущий (если применимо)
    val attachments: Attachments? = null, //Медиавложения комментария (фотографии, ссылки и т.п.)
    val parents_stack: IntArray? = null, //Массив идентификаторов родительских комментариев
    val thread: Thread? = null //Информация о вложенной ветке комментариев
)

// Информация о вложенной ветке комментариев
data class Thread(
    val count: Int, //количество комментариев в ветке
    val items: Array<Comment>,//массив объектов комментариев к записи
    val canPost: Boolean, //может ли текущий пользователь оставлять комментарии в этой ветке
    val showReplyButton: Boolean, //нужно ли отображать кнопку «ответить» в ветке
    val groupsCanPost: Boolean, //могут ли сообщества оставлять комментарии в ветке
)

data class Post(
    val id: Int = 10, // Идентификатор записи
    val ownerId: Int = 1, // Идентификатор владельца стены, на которой размещена запись
    val fromId: Int = 2, //Идентификатор автора записи
    val createdBy: Int = 3, //Идентификатор администратора, который опубликовал запись
    val date: Int = 1590075360, // Время публикации записи в формате unixtime
    val text: String = "New empty post", // Текст записи
    val replyOwnerId: Int = 4, //Идентификатор владельца записи, в ответ на которую была оставлена текущая
    val replyPostId: Int = 5, //Идентификатор записи, в ответ на которую была оставлена текущая
    val friendsOnly: Boolean = false, // true, если запись была создана с опцией «Только для друзей»
    val comments: Comments, // Комментарии к записи
    val copyright: Copyright, //Источник материала
    val likes: Likes, // Информация о лайках к записи
    val reposts: Reposts?, //Информация о репостах записи («Рассказать друзьям»)
    val views: Views, //Информация о просмотрах записи
    val postType: String = "post", // Тип записи, может принимать следующие значения: post, copy, reply, postpone, suggest
    val postSource: PostSource?, //Информация о способе размещения записи.Поле возвращается только для Standalone-приложений с ключом доступа, полученным в Implicit Flow.
    val geo: Geo, //Информация о местоположении
    val signerId: Int = 0, //Идентификатор автора, если запись была опубликована от имени сообщества и подписана пользователем
    val copyHistory: Array<Post>?, //Массив, содержащий историю репостов для записи.
    val canPin: Boolean = false, // Может ли текущий пользователь закрепить запись
    val canDelete: Boolean = false, // Может ли текущий пользователь удалить запись
    val canEdit: Boolean = false, // Может ли текущий пользователь редактировать запись
    val isPinned: Boolean = false, //true - Если запись закреплена
    val markedAsAds: Boolean = false, //true - Если запись содержит отметку "реклама"
    val isFavorite: Boolean = false, //true, если объект добавлен в закладки у текущего пользователя
    val donut: Donut, //Информация о записи VK Donut
    val postponedId: Boolean = false, //Идентификатор отложенной записи
    val attachements: Array<Attachments> = emptyArray() // вложения
)


object WallService {
    private var posts = emptyArray<Post>()
    private var comments = emptyArray<Comment>()
    private var counter = 0

    fun add(post: Post): Post {
        posts += post.copy(id = ++counter)
        return posts.last()
    }

    fun update(changedPost: Post): Boolean {
        for ((index, post) in posts.withIndex()) {
            if (changedPost.id == post.id) {
                posts[index] = changedPost.copy(ownerId = post.ownerId, date = post.date)
                return true
            }
        }
        return false
    }

    fun printAllPosts() {
        for (post in posts) println(post)
    }

    fun clear() {
        posts = emptyArray()
        counter = 0
    }

    fun createComment(postId: Int, comment: Comment): Comment {
        for (post in posts) {
            if (post.id == postId) {
                comments += comment
                return comment
            }
        }
        return throw PostNotFoundException()
    }
}

class PostNotFoundException() : RuntimeException()

fun main() {

    val likes = Likes()
    val comments = Comments()
    val copyright = Copyright()
    val reposts = Reposts()
    val views = Views()
    val postSource = PostSource()
    val geo = Geo()
    val copyHistory = emptyArray<Post>()
    val donut = Donut()
    val comment = Comment()
    val myPost = Post(
        id = 1,
        likes = likes,
        comments = comments,
        copyright = copyright,
        reposts = reposts,
        views = views,
        postSource = postSource,
        geo = geo,
        copyHistory = copyHistory,
        donut = donut
    )
    println(myPost)

    likes.count = 10
    comments.count = 3
    println(likes.count)
    println(comments.count)

    WallService.add(
        Post(
            comments = comments,
            likes = likes,
            copyright = copyright,
            reposts = reposts,
            views = views,
            postSource = postSource,
            geo = geo,
            copyHistory = copyHistory,
            donut = donut
        )
    )
    WallService.add(
        Post(
            id = 10,
            comments = comments,
            likes = likes,
            text = "It's the second post with id",
            copyright = copyright,
            reposts = reposts,
            views = views,
            postSource = postSource,
            geo = geo,
            copyHistory = copyHistory,
            donut = donut
        )
    )
    println(myPost)
    println()
    WallService.printAllPosts()
    WallService.createComment(postId = 2, comment)
    println(comment)
    WallService.createComment(postId = 0, comment)
}