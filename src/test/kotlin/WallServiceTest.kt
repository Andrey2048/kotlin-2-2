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
        val myPost = Post(id = 10, likes = likes, comments = comments)
        val result: Boolean

        WallService.add(myPost)
        result = (myPost.id > 0)
        assertTrue(result)
    }

    @Test
    fun update_postShouldBeUpdate() {
        val likes = Likes()
        val comments = Comments()
        val result: Boolean
        WallService.add(Post(likes = likes, comments = comments))
        WallService.add(Post(likes = likes, comments = comments))
        result = WallService.update(Post(id = 2, likes = likes, comments = comments, text = "Updated post"))
        assertTrue(result)
    }

    @Test
    fun update_postShouldBeNotUpdate() {
        val likes = Likes()
        val comments = Comments()
        val result: Boolean
        WallService.add(Post(likes = likes, comments = comments))
        WallService.add(Post(likes = likes, comments = comments))
        result = WallService.update(Post(id = 5, likes = likes, comments = comments, text = "Updated post"))
        assertFalse(result)
    }

}