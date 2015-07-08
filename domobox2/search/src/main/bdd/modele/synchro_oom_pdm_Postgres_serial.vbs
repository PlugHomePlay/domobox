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
            'msgBox tgm.Code
            'msgBox tgm.TargetModelFileName
            'msgBox "Code : " & tgm.Code & " TargetModelObject.ClassKind : " & tgm.TargetModelObject.ClassKind & " PdPDM.cls_Model : " & PdPDM.cls_Model & " File name : " & tgm.TargetModelFileName
            if (tgm.TargetModelObject.ClassKind = PdPDM.cls_Model) then
               set pdm = OpenModel(tgm.TargetModelFileName)
               'if (pdm.Iskindof(PDPdm.cls_Model)) then  
                  synchro mdl,pdm
                    output "nom modele pdm : " & pdm.ShortDescription
  
                             For each Pack in pdm.Packages
                             output "pack : "+ pack.Name
                             For each tab in pack.Tables
                                 if tab.IsShortcut()<>true then
                                 output "table : " & tab.Code
                                       ' SEY : Modification spécifique à FICEN
                                       ' Spécification d'un FileGroup Sql Server particulier pour la table stockant les BLOB de fichier.
                                       if tab.Code = "FILE_DATA" then                                    
                                          tab.PhysicalOptions = "on FICHIER"
                                          output "Ajout du FileGroup FICHIER sur la table FILE_DATA."                                          
                                       end if
                                       for each key in tab.Keys
                                             if key.primary then
                                                if key.Columns.Count = 1 then
                                                   For each col in  key.Columns
                                                      output "colonne clé : " & col.code & " domaine : " & col.Domain.Code
                                                  if col.Domain.Code = "DO_IDENTIFIANT" then
                                                         col.identity=true
                                                      end if
                                                   Next
                                                end if
                                                
                                            end if
                                       next
                                  end if
                             NEXT
                             NEXT
                  Exit For
               'end If
            end if
         Next
         If not (pdm.Iskindof(PDPdm.cls_Model)) then
            MsgBox "The target model is not a pdm : " + mdl.FileName 
         End If
      else
         MsgBox "At least a Pdm must be a target model"
      end if
   else
      MsgBox "The Active Model must be an oom : " + mdl.FileName  
   end if
End If

Private Sub synchro(oom,pdm)
   'output "Synchronizing " & oom.code & " and " & pdm.code
   Dim asso
   Dim ref 
   Dim role 
   
   dim childColumn 
   dim parentColumn 
   
   dim sequen
   dim clss
   dim test 
   dim loop_sequence
   
   if lenB(dbms) = 0 then
      dbms = pdm.DBMS.Name
    end if
   
  ' For Each clss in oom.Classes
  '    test = "false"
  '    if clss.isKindOf(cls_Shortcut) then
  '       test="true"
  '    end if
  '    for each loop_sequence in pdm.Sequences
  '       if loop_sequence.Name = "s_" & clss.Name then
  '          test = "true"
  '          Output "Séquence existe : " & loop_sequence.Name
  '       end if
  '    NEXT
  '    if test = "false" then
  '       set sequen = pdm.CreateObject(PdPDM.cls_Sequence)
  '       sequen.Name = "s_" & clss.Name
  '       sequen.Code = "S_" & clss.Code
  '       if dbms = "HSQL ADS" then
  '           sequen.PhysicalOptions = "start with 1000"
  '      else
  '          sequen.PhysicalOptions = "start with 1000 cache 20"
  '       end if
  '       Output "Séquence crée : " & sequen.Name
  '    end if
  ' NEXT
  
                  
   For Each asso In oom.Associations
      output "association : " & asso.code
       if (Instr(1,asso.roleAmultiplicity,"1") > 0 or Instr(1,asso.roleBmultiplicity,"1")) then
       For Each ref in pdm.References
         if (uCase(asso.code) = ref.code) then
           
             if (Instr(1,asso.code, "_") = 4 and Instr(5,asso.code, "_") = 8) then
             
                output "nom de colonnes et de FK à renommmer : cas 1"

                if  (ref.joins.count = 1) then
                  output "Traitement"
                  ref.ForeignKeyConstraintName = "FK_" & ref.code
                  
                  set childColumn = ref.joins.item(0).ChildTableColumn 
                  set parentColumn = ref.joins.item(0).ParentTableColumn  
                  role = mid(asso.code,9,len(asso.code))
                
                  if not (childColumn is nothing) then   
                
                     if not (instr(1,childColumn.code,"_id_" & role) > 0) then
                        OutPut childColumn.parent.code & "." & childColumn.code  & " --> (code) " & parentColumn.code & "_" & ucase(role)  
                        childColumn.Code =  parentColumn.code & "_" & ucase(role)  
                     end if
                     if not (instr(1,childColumn.name,"_id_" & role) > 0) then
                        OutPut childColumn.parent.name & "." & childColumn.name  & " --> (name) " & parentColumn.name & " " & lcase(role) 
                        childColumn.name =  parentColumn.name & " " & lcase(role)  
                     end if
                 
                  end if
                end if
              end if
              exit for
          end if
        next
        end if 
   next
   For Each asso In oom.Associations
       if (Instr(1,asso.roleAmultiplicity,"1") > 0 or Instr(1,asso.roleBmultiplicity,"1")) then
       For Each ref in pdm.References
         if (uCase(asso.code) = ref.code) then
             ' synchronisation des associations 0..1 ou 1..1 de l'OOM trigramme1_trigramme2 sans rôle avec les références du PDM
            if (Instr(1,asso.code, "_") = 4 and (len(asso.code) = 7) ) then
            
                  output "nom de colonne à renommmer : cas 2"
                  
                  if (ref.Iskindof(cls_Reference)) then
                  if ( ref.joins.count = 1) then
                  output "traitement"
                     set childColumn = ref.joins.item(0).ChildTableColumn 
                     set parentColumn = ref.joins.item(0).ParentTableColumn    
                  
                     if not (childColumn is nothing) then 
                        ' le code de la colonne migrée ne correspond pas au code de la clé de la table d'origine
                        if (childColumn.code <> parentColumn.code) then
                           ' recherche de la colonne d'origine dans la table
                           dim found, col
                           found = false
                           for each col in childColumn.parent.columns
                              if (col.code = parentColumn.code) then
                                 found = true
                                 OutPut "Impossible de renommer la colonne" & childColumn.parent.name & "." & childColumn.code & " veuillez assigner un rôle sur l'association dans le modèle objet"
                                 exit for
                              end if   
                           next
                           if (found = false) then
                              OutPut childColumn.parent.name & "." & childColumn.code  & " --> (code) " & parentColumn.code 
                              childColumn.Code =  parentColumn.code 
                              OutPut childColumn.parent.name & "." & childColumn.name  & " --> (name) " & parentColumn.code 
                              childColumn.name =  parentColumn.name 
                           end if
                        end if
                     end if 
                  end if
                  end if
            end if
            exit for
         end if
       next 
       end if
   Next
   ' go into the sub-packages
   Dim foom ' running oom folder
   Dim fpdm ' running pdm folder
   For Each foom In oom.Packages
      'calling sub procedure to scan children package
      For Each fpdm In pdm.Packages
      'calling sub procedure to scan children package
       if (uCase(foom.code) = fpdm.code) then
         synchro foom, fpdm
         exit for
       end if
      next
   Next   

End Sub

'-----------------------------------------------------------------------------
' Sub procedure to print information on current object in output
'-----------------------------------------------------------------------------
Private Sub DescribeObject(CurrentObject)
   if not CurrentObject.Iskindof(cls_NamedObject) then output "not an named object !" end if
  
      output "Checking "+CurrentObject.ClassName+" """+CurrentObject.Code+""", Created by "+CurrentObject.Creator+" On "+Cstr(CurrentObject.CreationDate)   
End Sub
