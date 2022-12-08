import org.junit.Assert
import org.junit.Before
import org.junit.Test

class WallServiceTest {

    @Before
    fun clearBeforeTest() {
        WallService.clear()
    }

    @Test
    fun add() {
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
            id = 10,
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
        val result: Boolean

        WallService.add(myPost)
        result = (myPost.id > 0)
        Assert.assertTrue(result)
    }

    @Test
    fun update_postShouldBeUpdate() {
        val likes = Likes()
        val comments = Comments()
        val copyright = Copyright()
        val reposts = Reposts()
        val views = Views()
        val postSource = PostSource()
        val geo = Geo()
        val copyHistory = emptyArray<Post>()
        val donut = Donut()

        WallService.add(
            Post(
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
        )
        WallService.add(
            Post(
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
        )
        Assert.assertTrue(
            WallService.update(
                Post(
                    id = 2,
                    likes = likes,
                    comments = comments,
                    text = "Updated post",
                    copyright = copyright,
                    reposts = reposts,
                    views = views,
                    postSource = postSource,
                    geo = geo,
                    copyHistory = copyHistory,
                    donut = donut
                )
            )
        )
    }

    @Test
    fun update_postShouldBeNotUpdate() {
        val likes = Likes()
        val comments = Comments()
        val copyright = Copyright()
        val reposts = Reposts()
        val views = Views()
        val postSource = PostSource()
        val geo = Geo()
        val copyHistory = emptyArray<Post>()
        val donut = Donut()
        val result: Boolean

        //Дважды добавляется пост, чтобы было 2 поста
        WallService.add(
            Post(
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
        )

        WallService.add(
            Post(
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
        )
        result = WallService.update(
            Post(
                id = 5,
                likes = likes,
                comments = comments,
                text = "Updated post",
                copyright = copyright,
                reposts = reposts,
                views = views,
                postSource = postSource,
                geo = geo,
                copyHistory = copyHistory,
                donut = donut
            )
        )
        Assert.assertFalse(result)
    }

    @Test(expected = PostNotFoundException::class)
    fun shouldThrow() {
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
        WallService.add(myPost)
        WallService.createComment(postId = 0, comment)
    }

    @Test
    fun createComment() {
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

        WallService.add(myPost) //появился пост с id=1
        WallService.add(myPost) //появился пост с id=2

        val resultComment = WallService.createComment(postId = 2, comment = comment)

        Assert.assertSame(resultComment, comment)

    }

}