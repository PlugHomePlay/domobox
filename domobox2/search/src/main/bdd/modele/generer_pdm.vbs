'******************************************************************************
'* File:     synchronisation oom_pdm.vbs
'* Purpose:  génération automatique des code de clés étrangères 
'* Title:    
'* Category: 
'* Version:  1.0
'* Company:  Kleegroup
'******************************************************************************

Option Explicit
ValidationMode = True
InteractiveMode = im_Batch

' get the current active model
Dim mdl ' the current model
Set mdl = ActiveModel
Dim pdm ' pdm
Dim tgm
Dim dbms
Dim dom
Dim tab
Dim col
Dim pack
Dim key

If (mdl Is Nothing) Then
   MsgBox "There is no Active Model"
Else
   if (mdl.Iskindof(PDOom.cls_Model) )then
      if (mdl.TargetModels.count > 0 ) Then
         For Each tgm in mdl.TargetModels
            'output PdPDM.cls_Model
            'output tgm.TargetModelClassKind
            'output tgm.TargetModelType + " | " + tgm.Code + " | " + tgm.TargetModelFileName
            'msgBox "Code : " & tgm.Code & " TargetModelObject.ClassKind : " & tgm.TargetModelObject.ClassKind & " PdPDM.cls_Model : " & PdPDM.cls_Model & " File name : " & tgm.TargetModelFileName
            
            if (tgm.TargetModelClassKind = PdPDM.cls_Model) then
               output "PDM trouvé : " & tgm.TargetModelFileName
               ' 1. Ouverture du PDM
               set pdm = OpenModel(tgm.TargetModelFileName)
               ' 2. Regénération du PDM à partir de l'OOM
               mdl.RegenerateModel pdm, Nothing, True               
               ' 3. Appel du script de synchronisation
               'Dim objShell
               'Set objShell = Wscript.CreateObject("WScript.Shell")
               'objShell.Run "D:\Projets\CSN\FICEN\Main\Ficen\src\main\bdd\modele\synchro_oom_pdm_MsSqlserver.vbs"
               ' Using Set is mandatory               
               'Set objShell = Nothing
               
               ' 4. Génération des scripts
            end if
            
            if (tgm.TargetModelType = PdPDM.cls_Model) then
               set pdm = OpenModel(tgm.TargetModelFileName)
               'if (pdm.Iskindof(PDPdm.cls_Model)) then                    
                    output "nom modele pdm : " & pdm.ShortDescription
                  Exit For
               'end If
            end if
         Next
         'If not (pdm.Iskindof(PDPdm.cls_Model)) then
          '  MsgBox "The target model is not a pdm : " + mdl.FileName 
         'End If
      else
         MsgBox "At least a Pdm must be a target model"
      end if
   else
      MsgBox "The Active Model must be an oom : " + mdl.FileName  
   end if
End If
