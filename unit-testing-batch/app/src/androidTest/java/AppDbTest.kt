import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ani.testing.batch.User
import com.ani.testing.batch.UserDao
import com.ani.testing.batch.UserDb
import org.junit.*
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters
import java.io.IOException

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(AndroidJUnit4::class)
class AppDbTest {

    lateinit var db : UserDb
    lateinit var dao : UserDao

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context,
            UserDb::class.java
        ).build()

        dao = db.userDao()
    }

    @Throws(IOException::class)
    @After
    fun closeDb() {
        db.close()
    }

    @Throws(Exception::class)
    @Test
    fun a_testCreateNewUser() {
        val user = User(
            13,
            "aa@dd.com",
            "acc111"
        )
        dao.createNewUser(user)
        val us = dao.findById(13)
        Assert.assertNotNull(us)
        Assert.assertEquals(us.email, "aa@dd.com")
    }

    @Throws(Exception::class)
    @Test
    fun b_testFindOperation() {

        val user = User(
            13,
            "aa@dd.com",
            "acc111"
        )
        dao.createNewUser(user)

        val sz = dao.findAll().size
        Assert.assertEquals(1, sz)
    }
}