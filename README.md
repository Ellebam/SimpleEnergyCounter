# SimpleEnergyCounter
A simple java desktop application for households/facilities to track and evaluate energy cosnumption

The application will show the opening panel on start where you are asked, if you want to create a new counter or to load a existing counter. 
Choosing to create a new counter will open a new frame where you can fill in your counters main informations: Counter Name, Type and Serial-Nr.
After comitting you rinformations you are asked where the new counter should be saved (new counter group or existing counter group). A counter group is essentially a 
agglomeration of counters stored in a save file. If you choose to create a new counter group you are asked for a name for that group.
In the end a dialog will open do determine a save location and name for the save file for this counter group. Afterwards the countergroup is shown in the overview of counters
of the selected countergroup. This screen will also be shown to you if you load a countergroup from a existing save file (from the opening panel "Load counter group").
The single counters are displayed as containers showing all relevant counter informations (Name, Type , Serial-Nr.). Editing the informations after creation is done
by clicking the "edit" button and clicking on the container which needs to be edited where you can choose which information to edit (Name or Type or Serial-Nr- or deleting counter).
Clicking on a container will open the overview of counter readings. To add a counter reading simply type in a value in the "insert value" textbox and choose a date from the "insert date" field and click on "Add". Thepanel is refreshed and will show you the added counter reading. Editing values is done by clicking the "edit" button and clicking on the individual counter reading entry and choosing an option from the opening dialog (edit value or date or deleting reading).

Adding new Counters to existing counter groups will enlarge the overview of counters. All readings only refer to their individual counters and won't have consequences on 
other counters
