function load(){
    loadReportedImages();
    loadReportedComments();

}
async function loadReportedImages(){
    picid = document.getElementById("curpic").getAttribute("title");
    let resp = await (fetch("reports?type=true")).then(response => response.json())
    .then(response => {
    
       
    }) 

}
async function loadReportedComments(){
    picid = document.getElementById("curpic").getAttribute("title");
    let resp = await (fetch("reports?type=false")).then(response => response.json())
    .then(response => {
    
       
    }) 
}