async function next(){
    picid = document.getElementById("curpic").getAttribute("title");
    let resp = await (fetch("nextpic?id=" + (parseInt(picid)))).then(response => response.json())
    .then(response => {
    
        console.log(response.url)
        document.getElementById("curpic").setAttribute("src" , response.url);
        document.getElementById("curpic").setAttribute("title" , parseInt(response.id));
        document.getElementById("curpic").setAttribute("alt" , response.description);
        getUserName(response.user);

        getComments();
    
    })  

}
async function getUserName(name){
    let userResp = await (fetch("uploader?uid=" + name)).then(response => response.json())
        .then(response => {
            document.getElementById("uploader").innerHTML = "uploaded by: " + response.userName;
        })
}
async function prev(){
    picid = document.getElementById("curpic").getAttribute("title");
    let resp = await (fetch("prevpic?id=" + (parseInt(picid)))).then(response => response.json())
    .then(response => {
    
        console.log(response.url)
        document.getElementById("curpic").setAttribute("src" , response.url);
        document.getElementById("curpic").setAttribute("title" , parseInt(response.id));
        document.getElementById("curpic").setAttribute("alt" , response.description);
        getUserName(response.user);
    
        getComments();
    })
}
async function rand() {
    let resp = await (fetch("randpic").then(response => response.json())
    .then(response => {
    
        console.log(response.url)
        document.getElementById("curpic").setAttribute("src" , response.url);
        document.getElementById("curpic").setAttribute("title" , parseInt(response.id));
        document.getElementById("curpic").setAttribute("alt" , response.description);
        getUserName(response.user);
    
        getComments();
    }))
}
async function mostRecent(){
    let resp = await (fetch("mostrecent").then(response => response.json())
    .then(response => {
    
        console.log(response.url)
        document.getElementById("curpic").setAttribute("src" , response.url);
        document.getElementById("curpic").setAttribute("title" , parseInt(response.id));
        document.getElementById("curpic").setAttribute("alt" , response.description);
        getUserName(response.user);
    
        getComments();
    }))
}
function swap(id){
    var clicked = document.getElementById(id);
    var centerpic = document.getElementById("curpic");
    var temp = clicked.getAttribute("src");
    clicked.setAttribute("src" , centerpic.getAttribute("src"));
    centerpic.setAttribute("src" , temp);

}
function upvote(){
    console.log("upvote clicked");
}
function downvote(){
    console.log("downvote Clicked");
}
function getCookie(cname){
    let cookies = document.cookie.split(';');
    for (i = 0; i < cookies.length ; i++){
        var cookie = cookies[i];
        
        while(cookie.charAt(0) == ' '){
            cookie = cookie.substring(1);
        }
        cookie = cookie.split('=');
        if(cookie[0] == cname){
            console.log("cookie found");
            return cookie[1];
        }
    }
    return null;
}
function setAdmin(){
    var admin = getCookie("admin");
    if(admin == "true"){
    
    const node = document.createElement("li");
    const link = document.createElement("a");
    const text = document.createTextNode("Admin Menu");
    link.appendChild(text);

    link.setAttribute("href" , "admin.html");
    node.appendChild(link);
    document.getElementById("preadmin").insertAdjacentElement('afterend' , node);
    }

}
function setUser(user){
    const node = document.createElement("h2");
    const greeting = document.createTextNode("Nice to see you again, " + user);
    node.appendChild(greeting);
    document.getElementById("userdiv").appendChild(node);

}

function loggedin(){
    console.log(document.cookie);
    var user = getCookie("user");
    if(user != null){
        var admin = getCookie("admin");
        setUser(user);
        setAdmin();
    }else{
        window.location.href = "index.html";

    }


}
async function getComments(){
    picid = document.getElementById("curpic").getAttribute("title");
    comments = document.getElementById("comments");
    addcomment = document.createElement("form");
    textbox = document.createElement("input");
    textbox.setAttribute("type" , "text");
    textbox.setAttribute("name" , "comment");

    textbox.setAttribute("value" , "Type new Comment here");
    
    submit = document.createElement("input");
    submit.setAttribute("type" , "submit");

    addcomment.appendChild(textbox);
    addcomment.appendChild(submit);
    addcomment.setAttribute("method" , "post");
    addcomment.setAttribute("action" , "addcomment?id=" + parseInt(picid));
    comments.innerHTML = "";

    comments.appendChild(addcomment);
    
    commentsheader = document.createElement("h3");
    commentsheader.innerHTML = "Comments:";

    comments.appendChild(commentsheader);
    

    let resp = await (fetch("comments?id=" + (parseInt(picid)))).then(response => response.json())
    .then(resp => {
        for(i = 0; i < resp.comments.length; i++){
            let response = fetch("uploader?uid=" + (parseInt(resp.comments[i].uID)));
            
            let p = document.createElement("p");
            
            p.innerHTML =  resp.comments[i].comment;

            
            comments.appendChild(p);

        }
    })

}
function report(type) {
    
}

function loaduser(){
    mostRecent();
    loggedin();
    
}
function load(){
    mostRecent();
    
}