/*
 * SOURCE: MURACH BOOK APPLICATION "ch07emailValidatePlus"
 * Java Servlets and JSP, Official text book for HNC year
 */

function validate(form)
{
    if (form.title.value === "")
    {
        alert("Please fill in your title");
        form.title.focus();
    } else if (form.firstName.value === "")
    {
        alert("Please fill in your first name");
        form.firstName.focus();
    } else if (form.surname.value === "")
    {
        alert("Please fill in your last name");
        form.surname.focus();
    } else if (form.mobileNo.value === "")
    {
        alert("Please fill in your mobile no");
        form.mobileNo.focus();
    } else if (form.homePhoneNumber.value === "")
    {
        alert("Please fill in your homephone no");
        form.homePhoneNumber.focus();
    } else if (form.emailAddress.value === "")
    {
        alert("Please fill in your email address");
        form.emailAddress.focus();
    } else if (form.loginName.value === "")
    {
        alert("Please fill in your login name");
        form.loginName.focus();
    } else if (form.loginPassword.value === "")
    {
        alert("Please fill in your login password");
        form.loginPassword.focus();
    } else if (form.dateOfBirth.value === "")
    {
        alert("Please fill in your date of birth");
        form.dateOfBirth.focus();
    } else
    {
        form.submit();
    }
}