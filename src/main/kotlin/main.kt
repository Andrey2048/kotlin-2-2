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

class Views(count: Int = 0)
{
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
    val place: String  = "" // описание места
)

class Donut(
    val isDonut: Boolean = false,
    val paidDuration: Int = 1000,
    val placeHolder: String = "",
    val canPublishFreeCopy: Boolean = false,
    val editMode: String = ""
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
)

object WallService {
    private var posts = emptyArray<Post>()
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
    }
}

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

    WallService.add(Post(
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
    WallService.add(Post(
        id = 10,
        comments = comments,
        likes = likes,
        text = "It's the second post",
        copyright = copyright,
        reposts = reposts,
        views = views,
        postSource = postSource,
        geo = geo,
        copyHistory = copyHistory,
        donut = donut
    ))
    println(myPost)
    WallService.printAllPosts()
}