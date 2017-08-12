/* 
 * SOURCE: MURACH BOOK APPLICATION "ch07emailValidatePlus"
 * Java Servlets and JSP, Official text book for HNC year
 */

function validate(form)
{
    /* */
    if (form.outboundFlightDate.value === "")
    {
        alert("Please fill in your date of travel");
        form.outboundFlightDate.focus();
    } else if (form.returnFlightDate.value === "")
    {
        alert("Please fill in your date of travel");
        form.returnFlightDate.focus();
    } else
    {
        form.submit();
    }
}