import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
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
        assertTrue(result)
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
        val result: Boolean

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
        assertTrue(result)
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
        assertFalse(result)
    }

}