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

data class Post(
    val id: Int = 1, // Идентификатор записи
    val ownerId: Int = 1, // Идентификатор владельца стены, на которой размещена запись
    val fromId: Int = 2, //Идентификатор автора записи
    val date: Int = 1590075360, // Время публикации записи в формате unixtime
    val text: String = "New empty post", // Текст записи
    val friendsOnly: Boolean = false, // true, если запись была создана с опцией «Только для друзей»
    val comments: Comments, // Комментарии к записи
    val likes: Likes, // Информация о лайках к записи
    val postType: String = "post", // Тип записи, может принимать следующие значения: post, copy, reply, postpone, suggest
    val canPin: Boolean = false, // Может ли текущий пользователь закрепить запись
    val canDelete: Boolean = false, // Может ли текущий пользователь удалить запись
    val canEdit: Boolean = false, // Может ли текущий пользователь редактировать запись
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
    val myPost = Post(id = 1, likes = likes, comments = comments)
    println(myPost)

    likes.count = 10
    comments.count = 3
    println(likes.count)
    println(comments.count)

    WallService.add(Post(comments = comments, likes = likes))
    WallService.add(Post(id = 10, comments = comments, likes = likes, text = "It's the second post"))
    println(myPost)
    WallService.printAllPosts()
}