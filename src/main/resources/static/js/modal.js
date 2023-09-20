// Obtenez des références aux éléments HTML pertinents
var openModalBtn = document.getElementById("openModalBtn");
var modal = document.getElementById("myModal");
var closeModalBtn = document.getElementsByClassName("close")[0];

// Ouvrir la modal lorsque le bouton est cliqué
openModalBtn.onclick = function() {
    modal.style.display = "block";
}

// Fermer la modal lorsque l'utilisateur clique sur le bouton de fermeture
closeModalBtn.onclick = function() {
    modal.style.display = "none";
}

// Fermer la modal lorsque l'utilisateur clique en dehors de la modal
window.onclick = function(event) {
    if (event.target === modal) {
        modal.style.display = "none";
    }
}