package com.example.doodlenoodle.ui

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.doodlenoodle.R
import com.example.doodlenoodle.data.NavItem
import com.example.doodlenoodle.data.entities.*
import com.example.doodlenoodle.models.DoodleNoodleViewModel
import com.example.doodlenoodle.ui.theme.primaryOrange
import kotlinx.coroutines.launch
import kotlin.random.Random


// Initial screen that is presented to the user
@Composable
fun WelcomeScreen(noodleViewModel: DoodleNoodleViewModel,navController: NavController){

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.DarkGray)
            .padding(16.dp)
        ,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Spacer(modifier = Modifier.padding(72.dp))
        Image(painter = painterResource(id = R.drawable.group_2),contentDescription = "")
        HomeScreenButtons(noodleViewModel,navController)
    }
}//WelcomeScreen

// Contains the buttons presented on at the homescreen
@Composable
fun HomeScreenButtons(noodleViewModel: DoodleNoodleViewModel,navController: NavController){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Button(onClick = {
            navController.navigate("newScreen")

        },
            colors = ButtonDefaults.buttonColors(backgroundColor = primaryOrange),
            shape = CutCornerShape(10)
        ){
            Text("New Board")
        }

        Button(onClick = {
            navController.navigate("joinScreen")
        },
            colors = ButtonDefaults.buttonColors(backgroundColor = primaryOrange),
            shape = CutCornerShape(10)
        ){
            Text("Join a Board")
        }
    }
}//HomeScreenButtons

// Screen that handles the user interaction required to create a group
@Composable
fun CreateGroupScreen(noodleViewModel: DoodleNoodleViewModel, navController: NavController) {

    val boardCode = remember { mutableStateOf(TextFieldValue()) }
    val maxBoardLength = 6
    val context = LocalContext.current
    val boardExist = noodleViewModel.getBoardByCode(boardCode.value.toString()).observeAsState()


    Column( modifier = Modifier
        .fillMaxSize()
        .background(Color.DarkGray)
        .padding(32.dp),

        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {

        Text("Enter a Group Code", fontSize = 48.sp, textAlign = TextAlign.Center ,color = Color.White)

        Spacer(modifier = Modifier.padding(8.dp))

        TextField(
            value = boardCode.value,
            onValueChange = {
                if(it.text.length <= maxBoardLength) boardCode.value = it
                else
                boardCode.value = it
            }
        )

        Spacer(modifier = Modifier.padding(8.dp))

        Button(onClick = {
            if(boardExist.value?.code != null){
                Toast.makeText(context, "This room code is already in use",Toast.LENGTH_LONG).show()
                boardCode.value = TextFieldValue("")
            } else{
                val newBoard = Board(0, boardCode.value.toString(),null,null)
                noodleViewModel.createBoard(newBoard)
                navController.navigate("boardList")
            }
        },
            colors = ButtonDefaults.buttonColors(backgroundColor = primaryOrange),
            shape = CutCornerShape(10)
        ){
            Text("Create Room", color = Color.White)
        }


    }
}//CreateGroupScreen

// Screen used to handle user interaction when joining a group
@Composable
fun JoinGroupScreen(noodleViewModel: DoodleNoodleViewModel,navController: NavController){
    val inputText = remember { mutableStateOf(TextFieldValue())}

    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.DarkGray)
        .padding(32.dp),

        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text("Enter a Group Code", fontSize = 48.sp, textAlign = TextAlign.Center ,color = Color.White)

        Spacer(modifier = Modifier.padding(16.dp))

        GroupCode(noodleViewModel,navController,inputText)


    }
}//JoinGroupScreen

// Contains the textField, button, and validation for joining a group
@Composable
fun GroupCode(noodleViewModel: DoodleNoodleViewModel,navController: NavController,inputText: MutableState<TextFieldValue>){
    val boardExist = noodleViewModel.getBoardByCode(inputText.value.toString()).observeAsState()
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        ){

        TextField(
            value = inputText.value,
            onValueChange = { inputText.value = it}
        )

        Button(onClick = {
            //Validate that room exists, if it does add it to the user's list of groups and navigate to that group screen
            // Else pop up an error
            if(boardExist.value?.code != null){
                navController.navigate("boardList")
            } else{
                inputText.value = TextFieldValue("")
                Toast.makeText(context, "This room doesn't exist!",Toast.LENGTH_LONG).show()
            }
        },
            colors = ButtonDefaults.buttonColors(backgroundColor = primaryOrange),
            shape = CutCornerShape(10)
        ){
            Text("Enter", color = Color.White)
        }
    }

}// Group Code

// Screen that displays all boards currently inside the database
@Composable
fun BoardList(noodleViewModel: DoodleNoodleViewModel,navController: NavController){
    val boardList = noodleViewModel.fetchAllBoards().observeAsState(arrayListOf())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.DarkGray)
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ) {
        Text("Board List", textAlign = TextAlign.Center, fontWeight = FontWeight.Bold, fontSize = 48.sp, color = Color.White)

        LazyColumn(
            content = {
            items(items = boardList.value,
                itemContent = {
                    Spacer( modifier = Modifier.padding(8.dp))
                    Row(
                        content = {
                            Box(content = {
                                Text(text = (it.code.toString()),
                                    fontSize = 12.sp,
                                 color = Color.White)
                            }, modifier = Modifier
                                .background(primaryOrange)
                                .clickable {
                                           navController.navigate("drawScreen")
                                },
                                contentAlignment = Alignment.Center
                            )
                        }
                    )
                })
        })
    }
}// BoardList

// Header used for the navigation drawer
@Composable
fun DrawerHeader() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 64.dp),
        contentAlignment =  Alignment.Center
    ){
        Text(text = "DoodleNoodle", fontSize = 48.sp)
    }
}//DrawerHeader

// Contains the drawer body
@Composable
fun DrawerBody(
    items: List<NavItem>,
    modifier: Modifier = Modifier,
    itemTextStyle: TextStyle = TextStyle(fontSize = 18.sp),
    onItemClick: (NavItem) -> Unit
) {
    LazyColumn(modifier){
        items(items) { item ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onItemClick(item)
                    }
                    .padding(16.dp)
            ){
                Icon(imageVector = item.icon,contentDescription = item.contentDescription)
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = item.title,
                    style = itemTextStyle,
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}//DrawerBody

// Used to hold the icon to toggle the drawer state
@Composable
fun AppBar(
    onNavigationIconClick: () -> Unit
) {
    TopAppBar(
        title = {
            Text(text = "Doodle Noodle")
        },
        backgroundColor = MaterialTheme.colors.primary,
        contentColor = MaterialTheme.colors.onPrimary,
        navigationIcon = {
            IconButton(onClick = onNavigationIconClick) {
                Icon(imageVector = Icons.Default.Menu, contentDescription = "Toggle Drawer")

            }
        }
    )
}//AppBar

// This is the initial screen that contains scaffold for the header. body, and bottom bar content
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ContentScreen(noodleViewModel: DoodleNoodleViewModel,navController: NavController){
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    val scope = rememberCoroutineScope()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            AppBar(
                onNavigationIconClick = {
                    scope.launch {
                        scaffoldState.drawerState.open()
                    }
                }
            )
                 },
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = { FloatingActionButton(onClick = {navController.navigate("joinScreen")}, backgroundColor = primaryOrange, contentColor = Color.White){
            Text("+")
        } },
        drawerContent = {
            DrawerHeader()
            DrawerBody(items = listOf(
                NavItem(
                    id = "boardList",
                    title = "Board List",
                    contentDescription = "Navigate to board list",
                    icon = Icons.Default.Home
                ),
                NavItem(
                    id = "joinScreen",
                    title = "Join Board",
                    contentDescription = "Join a Board",
                    icon = Icons.Default.Add
                )
            ), onItemClick = {
                navController.navigate(it.id)
            })
                        },
        content = {WelcomeScreen(noodleViewModel,navController) }
    )
}//ContentScreen

